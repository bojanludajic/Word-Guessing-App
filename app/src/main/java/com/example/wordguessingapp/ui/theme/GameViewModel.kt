package com.example.wordguessingapp.ui.theme

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


enum class Result {
    GENIUS, GREAT, IMPRESSIVE, CLOSE, PHEW
}



class GameViewModel : ViewModel() {

    private val _curWord: MutableState<String> = mutableStateOf("")
    val curWord: MutableState<String> get() = _curWord


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


}