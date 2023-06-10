package com.example.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.ViewModel.MainViewModel
import com.example.notesapp.ViewModel.ViewModelFactory
import com.example.notesapp.databinding.FragmentEditBinding
import com.example.notesapp.db.Note
import com.example.notesapp.db.NotesDatabase
import com.example.notesapp.repository.NotesRepository
import java.text.SimpleDateFormat
import java.util.*


class EditFragment : Fragment() {
    val notes by navArgs<EditFragmentArgs>()
    lateinit var editBinding: FragmentEditBinding
    private val editviewmodel by viewModels<MainViewModel>()
    private val currentdate = SimpleDateFormat.getInstance().format(Date())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = activity as MainActivity
        val notesRepository = NotesRepository(NotesDatabase(activity) as NotesDatabase)
        val viewModelFactory = ViewModelFactory(notesRepository)
        val editviewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        editBinding = FragmentEditBinding.inflate(layoutInflater, container, false)
        editBinding.editEdittitle.setText(notes.data.title)
        editBinding.editEditNotes.setText(notes.data.content)

        editBinding.editFloatingdonebtn.setOnClickListener {
            updateNote(it)
        }
        return editBinding.root
    }

    private fun updateNote(it: View?) {
        val title = editBinding.editEdittitle.text.toString()
        val content = editBinding.editEditNotes.text.toString()

        editviewmodel.saveNotes(
            Note(
                notes.data.id, editBinding.editEdittitle.text.toString(),
                editBinding.editEditNotes.text.toString(), currentdate
            )
        )
        Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_homeFragment)

    }


}