package com.example.notesapp.utils

object ColorPicker {
    val colors = arrayOf("#D4C4E3","#A8DAF0","#F0C390","#C3F09C","#F8961E","#FF018786")
    var currentColorIndex = 0

    fun getColor() : String{
        currentColorIndex = (currentColorIndex + 1)% colors.size
        return colors[currentColorIndex]
    }
}