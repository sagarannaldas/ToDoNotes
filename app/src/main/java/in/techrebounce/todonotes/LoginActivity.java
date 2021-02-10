package in.techrebounce.todonotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static in.techrebounce.todonotes.PrefConstant.IS_LOGGED_IN;
import static in.techrebounce.todonotes.PrefConstant.SHARED_PREFERENCE_NAME;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    EditText editTextFullName, editTextUserName;
    Button buttonLogin;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextFullName = findViewById(R.id.editTextFullName);
        editTextUserName = findViewById(R.id.editTextUserName);
        buttonLogin = findViewById(R.id.buttonLogin);
        setupSharedPreferences();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = editTextFullName.getText().toString();
                String userName = editTextUserName.getText().toString();

                if(!TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(userName)) {
                    Intent intent = new Intent(LoginActivity.this, MyNotesActivity.class);
                    intent.putExtra(AppConstant.FULL_NAME, fullName);
                    startActivity(intent);
                    saveLoginStatus();
                    savefullName(fullName);
                } else {
                    Toast.makeText(LoginActivity.this, "Username and fullname cant be empty",Toast.LENGTH_SHORT).show();
                }
            }
        };

        buttonLogin.setOnClickListener(clickListener);
    }

    private void savefullName(String fullName) {
        editor = sharedPreferences.edit();
        editor.putString(PrefConstant.FULL_NAME,fullName);
        editor.apply();
    }

    private void saveLoginStatus() {
        editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.apply();
    }

    private void setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME,MODE_PRIVATE);
    }
}