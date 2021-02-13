package `in`.techrebounce.todonotes

import `in`.techrebounce.todonotes.PrefConstant.FULL_NAME
import `in`.techrebounce.todonotes.PrefConstant.IS_LOGGED_IN
import `in`.techrebounce.todonotes.PrefConstant.SHARED_PREFERENCE_NAME
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    lateinit var editTextFullName : EditText
    lateinit var editTextUserName : EditText
    lateinit var buttonlogin : Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor :SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindViews()
        setupSharedPreferences()
    }

    private fun bindViews() {
        editTextFullName = findViewById(R.id.editTextFullName)
        editTextUserName =  findViewById(R.id.editTextUserName)
        buttonlogin = findViewById(R.id.buttonLogin)

        val clickListener = object : View.OnClickListener{
            override fun onClick(v: View?) {
                val fullName = editTextFullName.text.toString()
                val userName = editTextUserName.text.toString()

                if(fullName.isNotEmpty() && userName.isNotEmpty()) {
                    val intent = Intent(this@LoginActivity, MyNotesActivity::class.java)
                    intent.putExtra(FULL_NAME, fullName)
                    startActivity(intent)
                    saveLoginStatus()
                    saveFullName(fullName)
                } else {
                    Toast.makeText(this@LoginActivity, "Username & Fullname compulsory", Toast.LENGTH_SHORT).show()
                }
            }

        }
        buttonlogin.setOnClickListener(clickListener)
    }

    private fun setupSharedPreferences() {
        sharedPreferences =  getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE)
    }

    private fun saveFullName(fullName: String) {
        editor = sharedPreferences.edit()
        editor.putString(FULL_NAME, fullName)
        editor.apply()
    }

    private fun saveLoginStatus() {
        editor = sharedPreferences.edit()
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.apply()
    }

}