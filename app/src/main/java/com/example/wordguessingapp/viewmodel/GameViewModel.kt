package com.example.wordguessingapp.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.wordguessingapp.data.words
import com.example.wordguessingapp.ui.theme.DarkerGreen
import com.example.wordguessingapp.ui.theme.DarkerRed
import com.example.wordguessingapp.ui.theme.DarkerYellow
import com.example.wordguessingapp.viewmodel.Result
import kotlin.random.Random


enum class Result {
    GENIUS, GREAT, IMPRESSIVE, CLOSE, PHEW
}


class GameViewModel : ViewModel() {

    private val _curWord: MutableState<String> = mutableStateOf("")
    val curWord: MutableState<String> get() = _curWord
    val solution: MutableState<String> = mutableStateOf("")

    var currentRow by mutableStateOf(0)

    var guessList = mutableListOf<String>("", "", "", "", "")

    var endMessage by mutableStateOf("")
    var solved = mutableStateOf(false)

    var letterColors: MutableMap<Char, Color> = ('A'..'Z').associateWith { Color.LightGray }.toMutableMap()
        private set

    var score by mutableStateOf(0)


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
                endMessage = Result.values().getOrNull(currentRow).toString()
                Log.d("SOLUTION SOLVED", "$endMessage")
                solved.value = true
                _curWord.value.forEach { it ->
                    letterColors[it] = DarkerGreen
                }
                guessList[currentRow] = _curWord.value
                score += 5 - currentRow
            } else if(currentRow <= 4) {
                guessList[currentRow] = _curWord.value
                currentRow += 1
                Log.d("GUESS LIST", guessList.toString())
                for(i in 0..4) {
                    val guessedChar = _curWord.value[i]
                    letterColors[guessedChar] = when {
                        guessedChar == solution.value[i] -> DarkerGreen
                        guessedChar in solution.value && letterColors[guessedChar] != DarkerGreen -> DarkerYellow
                        guessedChar in solution.value && letterColors[guessedChar] == DarkerGreen -> DarkerGreen
                        else -> Color.DarkGray
                    }
                }
                _curWord.value = ""
            } else if(currentRow == 5) {
                endMessage = "Next time!"
            }
        }
    }

    fun checkColor() {

    }

    fun generateWord() {
        val randIndex = Random.nextInt(words.size)
        solution.value = words[randIndex]
        _curWord.value = ""
        guessList = mutableListOf("", "", "", "", "")
        letterColors = resetColors()
        Log.d("GENERATED SOLUTION", solution.value)
        solved.value = false
    }

    private fun resetColors(): MutableMap<Char, Color> {
        return ('A'..'Z').associateWith { Color.LightGray }.toMutableMap()
    }




}