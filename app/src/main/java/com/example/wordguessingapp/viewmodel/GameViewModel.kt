package com.example.wordguessingapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.wordguessingapp.data.parse
import com.example.wordguessingapp.ui.theme.DarkerGray1
import com.example.wordguessingapp.ui.theme.DarkerGray2
import com.example.wordguessingapp.ui.theme.DarkerGreen
import com.example.wordguessingapp.ui.theme.DarkerYellow
import kotlin.random.Random


enum class Result {
    GENIUS, GREAT, IMPRESSIVE, CLOSE, PHEW
}


class GameViewModel(application: Application) : AndroidViewModel(application) {

    val words: List<String> = parse(application)

    private val _curWord: MutableState<String> = mutableStateOf("")
    val curWord: MutableState<String> get() = _curWord

    private val _darkMode: MutableState<Boolean> = mutableStateOf(false)
    val darkMode: MutableState<Boolean> get() = _darkMode

    val solution: MutableState<String> = mutableStateOf("")

    var currentRow by mutableIntStateOf(0)

    var guessList = MutableList(5) { " " }
        private set

    var endMessage by mutableStateOf("")
        private set

    var solved: MutableState<Boolean> = mutableStateOf(false)
        private set
    var failed: MutableState<Boolean> = mutableStateOf(false)
        private set

    var letterColors: MutableMap<Char, Color> = ('A'..'Z').associateWith { Color.LightGray }.toMutableMap()
        private set

    var rowColors: MutableState<List<MutableList<Color>>> = mutableStateOf(List(5) { MutableList(5) { Color.White } })
        private set

    var wins: Int by mutableIntStateOf(0)
        private set
    var wordCount: Int by mutableIntStateOf(0)
        private set

    var wordTooShort: MutableState<Boolean> = mutableStateOf(false)
    var wrongWord: MutableState<Boolean> = mutableStateOf(false)


    val backGroundColor:MutableState<Color> = mutableStateOf(Color.White)
    val letterColor: MutableState<Color> = mutableStateOf(Color.Black)


    init {
        generateWord()
    }

    fun addLetter(c: Char) {
        if(_curWord.value.length < 5 && !solved.value && !failed.value) {
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
        if(solved.value || failed.value) {
            return
        }
        if(_curWord.value.length == 5 && words.contains(_curWord.value)) {
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
            } else {
                guessList[currentRow] = _curWord.value
                currentRow += 1
                if(currentRow == 5) {
                    failed.value = true
                    endMessage = "Nice try! Solution: ${solution.value}"
                }

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
            }
        } else {
            wrongWord.value = !words.contains(_curWord.value)
            wordTooShort.value = _curWord.value.length < 5
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
        rowColors.value[currentRow].clear()
        rowColors.value[currentRow].addAll(result)
    }

    fun generateWord() {
        val randIndex = Random.nextInt(words.size)
        solution.value = words[randIndex]
        _curWord.value = ""
        guessList = MutableList(5) {"     "}
        letterColors = resetColors()
        resetRows()
        Log.d("GENERATED SOLUTION", solution.value)
        solved.value = false
        failed.value = false
        currentRow = 0
        wordCount++
    }

    private fun resetColors(): MutableMap<Char, Color> {
        return ('A'..'Z').associateWith { Color.LightGray }.toMutableMap()
    }

    private fun resetRows() {
        rowColors.value = List(5) { mutableStateListOf(Color.White, Color.White, Color.White, Color.White, Color.White) }
    }

    public fun toggleDarkMode() {
        _darkMode.value = !_darkMode.value
        if(_darkMode.value) {
            backGroundColor.value = DarkerGray1
            letterColor.value = Color.White
        } else {
            backGroundColor.value = Color.White
            letterColor.value = Color.Black
        }
        Log.d("DARKMODE", darkMode.value.toString())
    }
}