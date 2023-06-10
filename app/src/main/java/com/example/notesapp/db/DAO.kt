package com.example.notesapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Notes")
    fun getallnote() : LiveData<List<Note>>

    @Update
    suspend fun updateNote(note: Note)

}