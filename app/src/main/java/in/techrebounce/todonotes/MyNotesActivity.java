package in.techrebounce.todonotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import in.techrebounce.todonotes.adapter.NotesAdapter;
import in.techrebounce.todonotes.clicklisteners.ItemClickListener;
import in.techrebounce.todonotes.model.Notes;

import static in.techrebounce.todonotes.AppConstant.DESCRIPTION;
import static in.techrebounce.todonotes.AppConstant.TITLE;

public class MyNotesActivity extends AppCompatActivity {

    private static final String TAG = "MyNotesActivity";
    String fullName;
    FloatingActionButton fabAddNotes;
    SharedPreferences sharedPreferences;
    RecyclerView recyclerViewNotes;
    ArrayList<Notes> notesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        bindViews();
        setupSharedPreference();
        getIntentData();

        fabAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpDialogBox();
            }
        });

        getSupportActionBar().setTitle(fullName);
    }

    private void setupSharedPreference() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        fullName = intent.getStringExtra(AppConstant.FULL_NAME);
        if (TextUtils.isEmpty(fullName)) {
            fullName = sharedPreferences.getString(PrefConstant.FULL_NAME, "");
        }
    }

    private void bindViews() {
        fabAddNotes = findViewById(R.id.fabAddNotes);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
    }

    private void setUpDialogBox() {
        View view = LayoutInflater.from(MyNotesActivity.this).inflate(R.layout.add_notes_dialog_layout, null);
        EditText editTextTitle = view.findViewById(R.id.editTextTitle);
        EditText editTextDescription = view.findViewById(R.id.editTextDescription);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);

        //dialog
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)) {
                    Notes notes = new Notes();
                    notes.setTitle(title);
                    notes.setDescription(description);
                    notesList.add(notes);
                } else {
                    Toast.makeText(MyNotesActivity.this, "Fields Can't Be Empty", Toast.LENGTH_SHORT).show();
                }

                setupRecyclerView();
                dialog.hide();
            }
        });

        dialog.show();
    }

    private void setupRecyclerView() {

        // interface
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(Notes notes) {
                Intent intent = new Intent(MyNotesActivity.this, DetailActivity.class);
                intent.putExtra(TITLE, notes.getTitle());
                intent.putExtra(DESCRIPTION, notes.getDescription());
                startActivity(intent);

            }
        };
        NotesAdapter notesAdapter = new NotesAdapter(notesList, itemClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyNotesActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewNotes.setLayoutManager(linearLayoutManager);
        recyclerViewNotes.setAdapter(notesAdapter);
    }
}