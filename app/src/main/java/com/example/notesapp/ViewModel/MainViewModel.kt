package com.example.notesapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.db.Note
import com.example.notesapp.repository.NotesRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NotesRepository): ViewModel() {

    fun saveNotes(newNote:Note) = viewModelScope.launch {
        repository.addNote(newNote)
    }

    fun updateNote(existingNote: Note) = viewModelScope.launch {
        repository.updateNote(existingNote)
    }

    fun deleteNote(existingNote: Note) = viewModelScope.launch {
        repository.deleteNote(existingNote)
    }

    fun getallNotes():LiveData<List<Note>> = repository.getNote()
}