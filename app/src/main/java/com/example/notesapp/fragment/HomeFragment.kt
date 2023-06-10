package com.example.notesapp.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.ViewModel.MainViewModel
import com.example.notesapp.ViewModel.ViewModelFactory
import com.example.notesapp.adapter.NotesAdapter
import com.example.notesapp.databinding.FragmentEditBinding
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.db.Note
import com.example.notesapp.db.NotesDatabase
import com.example.notesapp.repository.NotesRepository
import com.example.notesapp.utils.SwipeToDelete


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homebinding: FragmentHomeBinding
    private val viewmodel by viewModels<MainViewModel>()
    private lateinit var nadapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homebinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val activity = activity as MainActivity
        val notesRepository = NotesRepository(NotesDatabase(activity) as NotesDatabase)
        val viewModelFactory = ViewModelFactory(notesRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewmodel.getallNotes().observe(viewLifecycleOwner) { notesList ->
            homebinding.notesrecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
            homebinding.notesrecyclerview.adapter = NotesAdapter(requireContext(), notesList)
            nadapter = NotesAdapter(requireContext(), notesList)

        }
        val swipetodelete = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                val current = nadapter.notesList[position]
                viewmodel.deleteNote(current)

            }
        }
        val itemTouchhelper = ItemTouchHelper(swipetodelete)
        itemTouchhelper.attachToRecyclerView(homebinding.notesrecyclerview)

        homebinding.floatingaddbtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)
        }

        return homebinding.root

    }

}





