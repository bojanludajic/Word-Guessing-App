package com.example.wordguessingapp.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wordguessingapp.data.words
import com.example.wordguessingapp.ui.theme.GameViewModel
import com.example.wordguessingapp.ui.theme.boldHeadlineLarge
import kotlin.random.Random

@Composable
fun mainScreen(modifier: Modifier, gameViewModel: GameViewModel) {
    val random = Random.nextInt(0, words.size)
    val word = words[random]
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {

        Text(
            text = "GUESS THE WORD",
            style = boldHeadlineLarge,
            modifier = Modifier.padding(bottom = 20.dp, top = 30.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { btnIndex ->
                val char = gameViewModel.curWord.value.getOrNull(btnIndex)
                Box(
                    modifier = Modifier
                        .width(75.dp)
                        .height(75.dp)
                        .padding(3.dp)
                        .border(2.dp, Color.Black),

                    ) {
                    Text(
                        text = char?.toString() ?: "",
                        modifier = Modifier
                            .align(Alignment.Center),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { btnIndex ->
                val char = gameViewModel.curWord.value.getOrNull(btnIndex)
                Box(
                    modifier = Modifier
                        .width(75.dp)
                        .height(75.dp)
                        .padding(3.dp)
                        .border(2.dp, Color.Black),

                    ) {
                    /* TODO */
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { btnIndex ->
                val char = gameViewModel.curWord.value.getOrNull(btnIndex)
                Box(
                    modifier = Modifier
                        .width(75.dp)
                        .height(75.dp)
                        .padding(3.dp)
                        .border(2.dp, Color.Black),

                    ) {
                    /* TODO */
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { btnIndex ->
                val char = gameViewModel.curWord.value.getOrNull(btnIndex)
                Box(
                    modifier = Modifier
                        .width(75.dp)
                        .height(75.dp)
                        .padding(3.dp)
                        .border(2.dp, Color.Black),

                    ) {
                    /* TODO */
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { btnIndex ->
                val char = gameViewModel.curWord.value.getOrNull(btnIndex)
                Box(
                    modifier = Modifier
                        .width(75.dp)
                        .height(75.dp)
                        .padding(3.dp)
                        .border(2.dp, Color.Black),

                    ) {
                    /* TODO */
                }
            }
        }
        keyboard(gameViewModel)
    }
}