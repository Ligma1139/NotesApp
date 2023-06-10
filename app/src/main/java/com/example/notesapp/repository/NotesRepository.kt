package com.example.notesapp.repository

import com.example.notesapp.db.Note
import com.example.notesapp.db.NotesDatabase

class NotesRepository(private val db: NotesDatabase) {

    fun getNote() = db.myNotesdao().getallnote()

    suspend fun addNote(note: Note) = db.myNotesdao().insertNote(note)

    suspend fun updateNote(note: Note) = db.myNotesdao().updateNote(note)

    suspend fun deleteNote(note: Note) = db.myNotesdao().deleteNote(note)

}