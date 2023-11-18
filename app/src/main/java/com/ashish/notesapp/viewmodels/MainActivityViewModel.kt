package com.ashish.notesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ashish.notesapp.roomdatabase.Notes
import com.ashish.notesapp.roomdatabase.NotesDataBase

class MainActivityViewModel( application: Application):AndroidViewModel(application) {
    lateinit var notesList:LiveData<List<Notes>>

    init {
        notesList=NotesDataBase.getDb(application).notesDao().getAllNotes()
    }
}