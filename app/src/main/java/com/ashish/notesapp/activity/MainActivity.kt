package com.ashish.notesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashish.notesapp.R
import com.ashish.notesapp.databinding.ActivityMainBinding
import com.ashish.notesapp.roomdatabase.Notes
import com.ashish.notesapp.viewmodels.MainActivityViewModel
import com.mancj.materialsearchbar.MaterialSearchBar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel
    lateinit var noteList: List<Notes>
    lateinit var adapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteList = ArrayList()
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        var observer = Observer<List<Notes>> {
            noteList = it
            adapter = RvAdapter(noteList, this)
            binding.rcvw.layoutManager = LinearLayoutManager(this)
            binding.rcvw.adapter = adapter
        }
        viewModel.notesList.observe(this, observer)

        adapter = RvAdapter(noteList, this)
        binding.rcvw.layoutManager = LinearLayoutManager(this)
        binding.rcvw.adapter = adapter
        binding.addNotes.setOnClickListener {
            val intent = Intent(this, AddNotes::class.java)
            startActivity(intent)
        }
        val searchBar = binding.searchBar
        searchBar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onButtonClicked(buttonCode: Int) {

            }

            override fun onSearchStateChanged(enabled: Boolean) {
                if (!enabled) {
                    noteList = noteList
                    adapter.updateData(noteList)
                } else {

                }
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                filterNotes(text.toString())
            }

        })
    }

    private fun filterNotes(query: String) {
        var filteredNotes = noteList.filter { note ->
            note.title!!.contains(query, ignoreCase = true) ||
                    note.description!!.contains(query, ignoreCase = true)
        }
        if (filteredNotes.isEmpty()) {
            binding.notesNotFound.visibility = View.VISIBLE
        } else {
            binding.notesNotFound.visibility = View.GONE
        }
        adapter.updateData(filteredNotes)

    }
}
