package com.ashish.notesapp.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.notesapp.roomdatabase.Notes
import com.ashish.notesapp.roomdatabase.NotesDataBase
import kotlinx.coroutines.launch

class AddNotesViewModel( application: Application):AndroidViewModel(application) {

    fun insert(notes: Notes, context: Context){

            NotesDataBase.getDb(context).notesDao().insertNotes(notes)
    }
    fun update(notes: Notes, context: Context){

        NotesDataBase.getDb(context).notesDao().updateNotes(notes)
    }
}