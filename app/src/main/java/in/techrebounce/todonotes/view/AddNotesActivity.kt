package `in`.techrebounce.todonotes.view

import `in`.techrebounce.todonotes.R
import `in`.techrebounce.todonotes.utils.AppConstant
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddNotesActivity : AppCompatActivity() {

    lateinit var editTextTitle : EditText
    lateinit var editTextDescription : EditText
    lateinit var buttonSubmit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        bindViews()
        clickListener()
    }

    private fun bindViews() {
        editTextTitle = findViewById(R.id.editTextTitle)
        editTextDescription =  findViewById(R.id.editTextDescription)
        buttonSubmit = findViewById(R.id.buttonSubmit)
    }

    private fun clickListener() {
        val clickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                val title = editTextTitle.text.toString()
                val description =  editTextDescription.text.toString()
                if(title.isNotEmpty() && description.isNotEmpty()) {
                    val intent = Intent()
                    intent.putExtra(AppConstant.TITLE, title)
                    intent.putExtra(AppConstant.DESCRIPTION, description)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this@AddNotesActivity, "Field's can't be empty", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
        buttonSubmit.setOnClickListener(clickListener)
    }
}