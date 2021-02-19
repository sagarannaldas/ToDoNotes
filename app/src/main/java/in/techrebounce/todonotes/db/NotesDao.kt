package `in`.techrebounce.todonotes.db

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
    fun deleteNotes(note: Note)
}