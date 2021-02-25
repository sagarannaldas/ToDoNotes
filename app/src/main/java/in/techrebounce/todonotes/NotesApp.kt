package `in`.techrebounce.todonotes

import `in`.techrebounce.todonotes.db.NotesDatabase
import android.app.Application
import com.androidnetworking.AndroidNetworking

class NotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext);
    }

    fun getNotesDb(): NotesDatabase {
        return NotesDatabase.getInstance(this)
    }
}