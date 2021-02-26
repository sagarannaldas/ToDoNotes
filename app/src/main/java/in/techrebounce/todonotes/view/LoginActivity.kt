package `in`.techrebounce.todonotes.view

import `in`.techrebounce.todonotes.R
import `in`.techrebounce.todonotes.utils.PrefConstant
import `in`.techrebounce.todonotes.utils.PrefConstant.FULL_NAME
import `in`.techrebounce.todonotes.utils.StoreSession
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    lateinit var editTextFullName: EditText
    lateinit var editTextUserName: EditText
    lateinit var buttonlogin: Button
//    lateinit var sharedPreferences: SharedPreferences
//    lateinit var editor :SharedPreferences.Editor

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
        StoreSession.init(this)
//        sharedPreferences =  getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE)
    }


    private fun saveFullName(fullName: String) {
        StoreSession.write(PrefConstant.FULL_NAME, fullName)
//        editor = sharedPreferences.edit()
//        editor.putString(PrefConstant.FULL_NAME, fullName)
//        editor.apply()
    }

    private fun saveLoginStatus() {
        StoreSession.write(PrefConstant.IS_LOGGED_IN, true)
//        editor = sharedPreferences.edit()
//        editor.putBoolean(IS_LOGGED_IN, true)
//        editor.apply()
    }

}