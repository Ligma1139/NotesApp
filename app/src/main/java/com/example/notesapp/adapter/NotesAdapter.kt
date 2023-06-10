package com.example.notesapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.NoteItemBinding
import com.example.notesapp.db.Note
import com.example.notesapp.fragment.HomeFragmentDirections
import com.example.notesapp.utils.ColorPicker


class NotesAdapter(val requireContext: Context, val notesList: List<Note>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(val notebinding : NoteItemBinding) : RecyclerView.ViewHolder(notebinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() =notesList.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = notesList[position]
        holder.notebinding.cardtitle.text = data.title
        holder.notebinding.cardcontent.text = data.content
        holder.notebinding.cardDate.text = data.date
        holder.notebinding.cardview.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))

        holder.notebinding.noteitemcardview.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(data)
            Navigation.findNavController(it).navigate(action)
        }

    }


}