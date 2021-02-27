package `in`.techrebounce.todonotes.data.local.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

// data access objects - DAO
@Dao
interface NotesDao {

    @Query("SELECT * from notesData")
    fun getAll(): List<Note>

    @Insert(onConflict = REPLACE)
    fun insert(note: Note)

    @Update
    fun updateNotes(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM notesData WHERE isTaskCompleted = :status")
    fun deleteNotes(status: Boolean)
}