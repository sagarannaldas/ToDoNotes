package `in`.techrebounce.todonotes.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val TAG = "MyFirebaseMessagingServ"
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, "onMessageReceived: ${message?.from}")
        Log.d(TAG, "onMessageReceived: ${message?.notification?.body.toString()}")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}