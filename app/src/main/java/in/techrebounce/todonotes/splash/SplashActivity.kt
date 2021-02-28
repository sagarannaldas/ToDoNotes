package `in`.techrebounce.todonotes.splash

import `in`.techrebounce.todonotes.R
import `in`.techrebounce.todonotes.data.local.pref.PrefConstant
import `in`.techrebounce.todonotes.data.local.pref.StoreSession
import `in`.techrebounce.todonotes.login.LoginActivity
import `in`.techrebounce.todonotes.onboarding.OnBoardingActivity
import `in`.techrebounce.todonotes.view.MyNotesActivity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class SplashActivity : AppCompatActivity() {
    private val TAG = "SplashActivity"
    lateinit var handler: Handler
    lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPrefernce()
        goToNextScreen()

        getFCMToken()
        setupNotification("This is local notification")
    }

    private fun goToNextScreen() {
        handler = Handler()
        runnable = Runnable {
            checkLoginStatus()
        }
        handler.postDelayed(runnable, 2000)
    }

    private fun setupNotification(body: String?) {
        val channelId = "local ID"
        val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Todo NotesApp")
                .setContentText(body)
                .setSound(ringtone)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Local Notes", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())

    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d(TAG, token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })
    }


    private fun setupSharedPrefernce() {
        StoreSession.init(this)
    }

    private fun checkLoginStatus() {
        // If a user is loggedIn show them MyNotesActivity else show LoginActivity
        val isLoggedIn = StoreSession.read(PrefConstant.IS_LOGGED_IN)
        val isBoardingSuccess = StoreSession.read(PrefConstant.ON_BOARDED_SUCCESSFULLY)
        if (isLoggedIn!!) {
            val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)
        } else if (isBoardingSuccess!!) {
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
            startActivity(intent)
        }
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        handler.removeCallbacks(runnable)
    }
}