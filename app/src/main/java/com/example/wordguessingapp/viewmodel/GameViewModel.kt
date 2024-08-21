package com.example.wordguessingapp.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var currentRow by mutableStateOf(0)

    val guessList = mutableListOf<String>("", "", "", "", "")

    var endMessage by mutableStateOf("")

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

    fun check() {
        if(_curWord.value.length == 5) {
            if(_curWord.value == solution.value) {
                endMessage = "Great job!"
                Log.d("SOLUTION SOLVED", "$endMessage")
            } else if(currentRow <= 4) {
                guessList[currentRow] = _curWord.value
                currentRow += 1
                Log.d("GUESS LIST", guessList.toString())
                _curWord.value = ""
            } else {
                endMessage = "Next time!"
            }
        }
    }

    fun generateWord() {
        val randIndex = Random.nextInt(words.size)
        solution.value = words[randIndex]
        Log.d("GENERATED SOLUTION", solution.value)
    }




}