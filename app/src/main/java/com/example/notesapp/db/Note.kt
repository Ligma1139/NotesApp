package com.example.notesapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var title: String,
    var content: String,
    var date: String,
    val color : Int = -1
) : java.io.Serializable
