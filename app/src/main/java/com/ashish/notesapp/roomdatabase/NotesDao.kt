package com.ashish.notesapp.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.util.concurrent.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM notes_table WHERE id = :id")
     fun getSpecificNote(id: Int): Notes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertNotes(notes: Notes)

    @Delete
     fun deleteNotes(notes: Notes)

    @Query("DELETE FROM notes_table WHERE id = :id")
     fun deleteSpecificNote(id: Int)

    @Update
     fun updateNotes(notes:Notes )
}