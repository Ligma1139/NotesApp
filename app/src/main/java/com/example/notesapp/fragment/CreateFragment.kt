package com.example.notesapp.fragment

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.ViewModel.MainViewModel
import com.example.notesapp.ViewModel.ViewModelFactory
import com.example.notesapp.databinding.FragmentCreateBinding
import com.example.notesapp.db.NotesDatabase
import com.example.notesapp.repository.NotesRepository
import java.text.SimpleDateFormat
import java.util.*

class CreateFragment : Fragment(R.layout.fragment_create) {
    private lateinit var createBinding: FragmentCreateBinding
    lateinit var navController: NavController
    private var note: Note? = null
    private var color = -1
    private val viewmodel by viewModels<MainViewModel>()
    private val currentdate = SimpleDateFormat.getInstance().format(Date())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createBinding = FragmentCreateBinding.inflate(layoutInflater, container, false)

        val activity = activity as MainActivity
        val notesRepository = NotesRepository(NotesDatabase(activity) as NotesDatabase)
        val viewModelFactory = ViewModelFactory(notesRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        createBinding.floatingdonebtn.setOnClickListener {
            addNote()
            Navigation.findNavController(it).navigate(R.id.action_createFragment_to_homeFragment)
        }
        return createBinding.root
    }


    private fun addNote() {

        val title = createBinding.edittitle.text.toString()
        val content = createBinding.editNotes.text.toString()

        viewmodel.saveNotes(
            com.example.notesapp.db.Note(
                0,
                createBinding.edittitle.text.toString(),
                createBinding.editNotes.text.toString(), currentdate, color
            )
        )


    }
}

