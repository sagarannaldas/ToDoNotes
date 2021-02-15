package `in`.techrebounce.todonotes

import `in`.techrebounce.todonotes.db.NotesDatabase
import android.app.Application

class NotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    fun getNotesDb(): NotesDatabase {
        return NotesDatabase.getInstance(this)
    }
}