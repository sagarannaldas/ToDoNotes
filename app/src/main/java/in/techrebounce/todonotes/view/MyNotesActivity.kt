package `in`.techrebounce.todonotes.view

import `in`.techrebounce.todonotes.NotesApp
import `in`.techrebounce.todonotes.utils.AppConstant.DESCRIPTION
import `in`.techrebounce.todonotes.utils.AppConstant.TITLE
import `in`.techrebounce.todonotes.utils.PrefConstant.FULL_NAME
import `in`.techrebounce.todonotes.utils.PrefConstant.SHARED_PREFERENCE_NAME
import `in`.techrebounce.todonotes.R
import `in`.techrebounce.todonotes.adapter.NotesAdapter
import `in`.techrebounce.todonotes.clicklisteners.ItemClickListener
import `in`.techrebounce.todonotes.db.Notes
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

public class MyNotesActivity : AppCompatActivity() {

    private val TAG = "MyNotesActivity"
    var fullName : String = ""
    lateinit var fabAddNotes: FloatingActionButton
    lateinit var sharedPreferences: SharedPreferences
    lateinit var recyclerViewNotes : RecyclerView
    var notesList = ArrayList<Notes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        bindViews()
        setupSharedPreference()
        getIntentData()
        getDataFromDataBase()
        setupRecyclerView()

        supportActionBar?.title = fullName

    }

    private fun getDataFromDataBase() {
        val notesApp = applicationContext as NotesApp
        val notesDao = notesApp.getNotesDb().notesDao()
        Log.d(TAG, "getDataFromDataBase: $notesDao.getAll().size.toString()")
        notesList.addAll(notesDao.getAll())
    }

    private fun bindViews() {
        fabAddNotes = findViewById(R.id.fabAddNotes)
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)

        val clickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                setupDialogBox()
            }

        }
        fabAddNotes.setOnClickListener(clickListener)
    }

    private fun setupDialogBox() {
        val view = LayoutInflater.from(this@MyNotesActivity).inflate(R.layout.add_notes_dialog_layout, null)
        val editTextTitle = view.findViewById<EditText>(R.id.editTextTitle)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextDescription)
        val buttonSubmit = view.findViewById<Button>(R.id.buttonSubmit)

        //dialog
        val dialog = AlertDialog.Builder(this@MyNotesActivity)
                .setView(view)
                .setCancelable(false)
                .create()

        val clickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                val title = editTextTitle.text.toString()
                val description = editTextDescription.text.toString()
                if(title.isNotEmpty() && description.isNotEmpty()) {
                    val notes = Notes(title = title, description = description)
                    addNotesToDb(notes)
                    notesList.add(notes)
                } else {
                    Toast.makeText(this@MyNotesActivity,"Fields Can't Be Empty",Toast.LENGTH_SHORT).show()
                }
                setupRecyclerView()
                dialog.hide()
            }

        }
        buttonSubmit.setOnClickListener(clickListener)
        dialog.show()
    }

    private fun addNotesToDb(notes: Notes) {
        // insert into db
        val notesApp = applicationContext as NotesApp
        val notesDao = notesApp.getNotesDb().notesDao()
        notesDao.insert(notes)

    }

    private fun setupRecyclerView() {
        val itemClickListener = object : ItemClickListener {
            override fun onClick(notes: Notes) {
                val intent = Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(TITLE,notes.title)
                intent.putExtra(DESCRIPTION, notes.description)
                startActivity(intent)
            }

            override fun onUpdate(notes: Notes) {
                //update the value
                Log.d(TAG, "onUpdate: ${notes.isTaskCompleted.toString()}")
                updateNotes(notes)
            }
        }

        val notesAdapter = NotesAdapter(notesList, itemClickListener)
        val linearLayoutManager = LinearLayoutManager(this@MyNotesActivity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerViewNotes.layoutManager = linearLayoutManager
        recyclerViewNotes.adapter = notesAdapter



    }

    private fun updateNotes(notes: Notes) {
        val notesApp = applicationContext as NotesApp
        val notesDao = notesApp.getNotesDb().notesDao()
        notesDao.updateNotes(notes)
    }

    private fun setupSharedPreference() {
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE)
    }

    private fun getIntentData() {
        val intent = intent
        if(intent.hasExtra(FULL_NAME)) {
            fullName = intent.getStringExtra(FULL_NAME)
        }
        if(fullName.isEmpty()) {
            fullName = sharedPreferences.getString(FULL_NAME,"").toString()
        }
    }
}