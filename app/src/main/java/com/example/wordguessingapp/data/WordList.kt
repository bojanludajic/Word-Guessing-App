package com.example.wordguessingapp.data

import android.content.Context
import com.example.wordguessingapp.R

const val WORD_LENGTH = 5

fun parse(context: Context): List<String> {
    val inputStream = context.resources.openRawResource(R.raw.words)
    return inputStream.bufferedReader().useLines { lines ->
        lines.map { it.toUpperCase() }.toList()
    }
}

