package com.example.wordguessingapp.ui.theme

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.wordguessingapp.data.words
import kotlin.random.Random


enum class Result {
    GENIUS, GREAT, IMPRESSIVE, CLOSE, PHEW
}



class GameViewModel : ViewModel() {

    private val _curWord: MutableState<String> = mutableStateOf("")
    val curWord: MutableState<String> get() = _curWord
    val solution: MutableState<String> = mutableStateOf("")

    init {
        generateWord()
    }


    fun addLetter(c: Char) {
        if(_curWord.value.length < 5) {
            _curWord.value += c
            Log.d("ADDED", c.toString())
            Log.d("CURRENT WORD", _curWord.value)
        }
    }

    fun removeLetter() {
        if(_curWord.value.length > 0) {
            Log.d("REMOVED", _curWord.value.takeLast(1))
            _curWord.value = _curWord.value.dropLast(1)
            Log.d("CURRENT WORD", _curWord.value)
        }
    }

    fun check() { /* TODO */ }

    fun generateWord() {
        val randIndex = Random.nextInt(words.size)
        solution.value = words[randIndex]
        Log.d("GENERATED SOLUTION", solution.value)
    }




}