package `in`.techrebounce.todonotes.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

// data access objects - DAO
@Dao
interface NotesDao {

    @Query("SELECT * from notesData")
    fun getAll() : List<Notes>

    @Insert(onConflict = REPLACE)
    fun insert(notes: Notes)

    @Update
    fun updateNotes(notes: Notes)

    @Delete
    fun deleteNotes(notes: Notes)
}