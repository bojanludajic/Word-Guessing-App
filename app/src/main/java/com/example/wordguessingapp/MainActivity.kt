package com.example.wordguessingapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wordguessingapp.data.firstRow
import com.example.wordguessingapp.data.secondRow
import com.example.wordguessingapp.data.thirdRow
import com.example.wordguessingapp.data.words
import com.example.wordguessingapp.ui.theme.GameViewModel
import com.example.wordguessingapp.ui.theme.LightOrange
import com.example.wordguessingapp.ui.theme.WordGuessingAppTheme
import com.example.wordguessingapp.ui.theme.boldHeadlineLarge
import com.example.wordguessingapp.view.keyboard
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordGuessingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    mainScreen(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}


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
                        modifier = Modifier.align(Alignment.Center)
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
                    Text(
                        text = char?.toString() ?: "",
                        modifier = Modifier.align(Alignment.Center)
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
                    Text(
                        text = char?.toString() ?: "",
                        modifier = Modifier.align(Alignment.Center)
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
                    Text(
                        text = char?.toString() ?: "",
                        modifier = Modifier.align(Alignment.Center)
                    )
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
                    Text(
                        text = char?.toString() ?: "",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        keyboard(gameViewModel)
    }

}