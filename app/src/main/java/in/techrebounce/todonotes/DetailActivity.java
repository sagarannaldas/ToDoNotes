package in.techrebounce.todonotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static in.techrebounce.todonotes.AppConstant.DESCRIPTION;
import static in.techrebounce.todonotes.AppConstant.TITLE;

public class DetailActivity extends AppCompatActivity {

    private TextView textViewTitle, textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindViews();
        setupIntentData();
    }

    private void setupIntentData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(TITLE);
        String description = intent.getStringExtra(DESCRIPTION);
        textViewTitle.setText(title);
        textViewDescription.setText(description);
    }

    private void bindViews() {
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
    }
}