package com.ashish.notesapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDataBase: RoomDatabase() {
    abstract fun notesDao():NotesDao

    companion object{
        public  var roomDb: NotesDataBase?=null
        fun getDb(context: Context):NotesDataBase{
            if (roomDb==null){
               roomDb=Room.databaseBuilder(
                   context,NotesDataBase::class.java,
                   "notes_databse"
               ).allowMainThreadQueries().build()
            }
            return roomDb!!
        }
    }
}