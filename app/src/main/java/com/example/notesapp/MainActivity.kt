package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notesapp.ViewModel.MainViewModel
import com.example.notesapp.ViewModel.ViewModelFactory
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.db.NotesDatabase
import com.example.notesapp.repository.NotesRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewmodel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)

        try {
            setContentView(binding.root)
            val notesRepository = NotesRepository(NotesDatabase(this) as NotesDatabase)
            val viewModelFactory = ViewModelFactory(notesRepository)
            viewmodel = ViewModelProvider(this,viewModelFactory)[viewmodel::class.java]
        }catch (e:Exception){
            Log.d("TAG", "onCreate: ERROR")
        }

    }
}