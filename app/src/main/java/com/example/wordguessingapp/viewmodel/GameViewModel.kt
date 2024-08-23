package com.example.wordguessingapp.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.wordguessingapp.data.words
import com.example.wordguessingapp.ui.theme.DarkerGreen
import com.example.wordguessingapp.ui.theme.DarkerYellow
import kotlin.random.Random


enum class Result {
    GENIUS, GREAT, IMPRESSIVE, CLOSE, PHEW
}


class GameViewModel : ViewModel() {

    private val _curWord: MutableState<String> = mutableStateOf("")
    val curWord: MutableState<String> get() = _curWord
    val solution: MutableState<String> = mutableStateOf("")

    var currentRow by mutableIntStateOf(0)

    var guessList = MutableList(5) { " " }

    var endMessage by mutableStateOf("")
    var solved: MutableState<Boolean> = mutableStateOf(false)

    var letterColors: MutableMap<Char, Color> = ('A'..'Z').associateWith { Color.LightGray }.toMutableMap()
        private set
    var rowColors: MutableList<MutableList<Color>> = MutableList(5) { MutableList(5) { Color.White } }

    var wins: Int by mutableIntStateOf(0)
    var wordCount: Int by mutableIntStateOf(0)

    init {
        generateWord()
    }

    fun addLetter(c: Char) {
        if(_curWord.value.length < 5 ) {
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
        if(_curWord.value.length == 5 && !solved.value) {
            checkColor(_curWord.value)
            if(_curWord.value == solution.value) {
                endMessage = Result.entries.getOrNull(currentRow).toString()
                Log.d("SOLUTION SOLVED", endMessage)
                solved.value = true
                _curWord.value.forEach { it ->
                    letterColors[it] = DarkerGreen
                }
                guessList[currentRow] = _curWord.value
                wins++
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
            } else if(currentRow == 4) {
                endMessage = "Next time!"
            }
        }
    }

    private fun checkColor(guess: String) {
        val result = MutableList<Color>(5) { Color.White }
        val solutionChars = solution.value.toMutableList()

        for(i in guess.indices) {
            if(guess[i] == solution.value[i]) {
                result[i] = DarkerGreen
                solutionChars[i] = '*'
            }
        }

        for(i in guess.indices) {
            if(result[i] != DarkerGreen) {
                val letterIndex = solutionChars.indexOf(guess[i])
                if(letterIndex != -1) {
                    result[i] = DarkerYellow
                    solutionChars[letterIndex] = '*'
                } else {
                    result[i] = Color.DarkGray
                }
            }
        }
        rowColors[currentRow] = result.toMutableList()
    }

    fun generateWord() {
        val randIndex = Random.nextInt(words.size)
        solution.value = words[randIndex]
        _curWord.value = ""
        guessList = MutableList(5) {"     "}
        letterColors = resetColors()
        rowColors = resetRows()
        Log.d("GENERATED SOLUTION", solution.value)
        solved.value = false
        currentRow = 0
        wordCount++
    }

    private fun resetColors(): MutableMap<Char, Color> {
        return ('A'..'Z').associateWith { Color.LightGray }.toMutableMap()
    }

    private fun resetRows(): MutableList<MutableList<Color>> {
        return MutableList(5) { MutableList(5) { Color.White } }
    }
}