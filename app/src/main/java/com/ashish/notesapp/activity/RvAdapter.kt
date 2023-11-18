package com.ashish.notesapp.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashish.notesapp.databinding.NotesSampleBinding
import com.ashish.notesapp.roomdatabase.Notes
import com.ashish.notesapp.roomdatabase.NotesDataBase


class RvAdapter(var notesList: List<Notes>,var context: Context):RecyclerView.Adapter<RvAdapter.MyViewHolder>() {
    lateinit var dailog:AlertDialog

    inner class MyViewHolder(var binding: NotesSampleBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding=NotesSampleBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.binding.tvTitle.text=notesList.get(position).title
        holder.binding.tvDesc.text=notesList.get(position).description
        //holder.binding.tvTimestamp.text= notesList.get(position).dateTime.toString()

        holder.itemView.setOnClickListener {
            var intent=Intent(context,AddNotes::class.java)
            intent.putExtra("NOTE_EDIT",notesList.get(position))
            context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            var dailog=AlertDialog.Builder(context)
            dailog.setTitle("Delete Note")
            dailog.setMessage("if you want to delete this note click on delete button or click on Cancel button for exit this")
            dailog.setIcon(android.R.drawable.sym_def_app_icon)
            dailog.setPositiveButton("Delete"){dailogInterface,which->
                NotesDataBase.getDb(context).notesDao().deleteSpecificNote(id = notesList.get(position).id!!)
            }
            dailog.setNeutralButton("Cancel"){dailogInterface,which->

            }
            val adailog=dailog.create()
            adailog.show()
            true
        }
    }

    fun updateData(newNoteList: List<Notes>) {
        notesList = newNoteList
        notifyDataSetChanged()
    }
}

