package com.ashish.notesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ashish.notesapp.databinding.ActivityAddNotesBinding
import com.ashish.notesapp.roomdatabase.Notes
import com.ashish.notesapp.viewmodels.AddNotesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddNotes : AppCompatActivity() {
    lateinit var binding: ActivityAddNotesBinding
    lateinit var addNotesViewModel:AddNotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNotesBinding.inflate(layoutInflater)
        binding.backBtn.setOnClickListener {
            var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(binding.root)

        addNotesViewModel=ViewModelProvider(this).get(AddNotesViewModel::class.java)
        if (intent.hasExtra("NOTE_EDIT")){
            var notes:Notes=intent.getSerializableExtra("NOTE_EDIT")as Notes
            binding.edtNotesTitle.setText(notes.title)
            binding.edtNotesDesc.setText(notes.description)
            binding.saveNotes.setOnClickListener {
                var title = binding.edtNotesTitle.text.toString()
                var desc = binding.edtNotesDesc.text.toString()

               notes.title=title
               notes.description=desc
                addNotesViewModel.update(notes, this)
                finish()
            }
        }else {
            binding.saveNotes.setOnClickListener {
                if (binding.edtNotesTitle.text!!.isEmpty()) {
                    Toast.makeText(this, "empty notes title", Toast.LENGTH_SHORT).show()
                    binding.edtNotesTitle.requestFocus()
                } else if (binding.edtNotesDesc.text!!.isEmpty()) {
                    Toast.makeText(this, "empty notes description", Toast.LENGTH_SHORT).show()
                    binding.edtNotesTitle.requestFocus()
                } else {

                    var title = binding.edtNotesTitle.text.toString()
                    var desc = binding.edtNotesDesc.text.toString()
                    var notes = Notes(title = title, description = desc)
                    addNotesViewModel.insert(notes, this)
                    finish()
                }

            }
        }
    }
}