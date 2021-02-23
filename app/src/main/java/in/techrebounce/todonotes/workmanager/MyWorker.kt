package `in`.techrebounce.todonotes.workmanager

import `in`.techrebounce.todonotes.NotesApp
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(val context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        val notesApp = applicationContext as NotesApp
        val notesDao = notesApp.getNotesDb().notesDao()
        notesDao.deleteNotes(true)
        return Result.success()
    }
}