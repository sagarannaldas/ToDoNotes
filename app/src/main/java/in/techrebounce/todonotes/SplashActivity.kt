package `in`.techrebounce.todonotes

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPrefernce()
        checkLoginStatus()
    }

    private fun setupSharedPrefernce() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, MODE_PRIVATE)
    }

    private fun checkLoginStatus() {
        // If a user is loggedIn show them MyNotesActivity else show LoginActivity
        var isLoggedIn = sharedPreferences.getBoolean(PrefConstant.IS_LOGGED_IN, false)
        if (isLoggedIn) {
            val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}