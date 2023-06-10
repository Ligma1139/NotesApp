package com.example.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun myNotesdao(): DAO

    companion object{
        @Volatile
        private var INSTANCE : RoomDatabase?= null
        private var Lock = Any()

        operator fun invoke(context: Context) = INSTANCE?: synchronized(Lock){
            INSTANCE?: getdatabase(context).also {
                INSTANCE = it
            }
        }

        private fun getdatabase(context: Context)  = Room.databaseBuilder(context.applicationContext,
            NotesDatabase::class.java,
            "notes_db").allowMainThreadQueries().build()
    }

}



