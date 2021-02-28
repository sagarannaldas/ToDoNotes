package `in`.techrebounce.todonotes.login

import `in`.techrebounce.todonotes.R
import `in`.techrebounce.todonotes.data.local.pref.PrefConstant
import `in`.techrebounce.todonotes.data.local.pref.PrefConstant.FULL_NAME
import `in`.techrebounce.todonotes.data.local.pref.StoreSession
import `in`.techrebounce.todonotes.view.MyNotesActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    lateinit var editTextFullName: EditText
    lateinit var editTextUserName: EditText
    lateinit var buttonlogin: Button

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
    }


    private fun saveFullName(fullName: String) {
        StoreSession.write(PrefConstant.FULL_NAME, fullName)
    }

    private fun saveLoginStatus() {
        StoreSession.write(PrefConstant.IS_LOGGED_IN, true)
    }

}