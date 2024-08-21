package com.example.wordguessingapp.view

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordguessingapp.data.firstRow
import com.example.wordguessingapp.data.secondRow
import com.example.wordguessingapp.data.thirdRow
import com.example.wordguessingapp.data.words
import com.example.wordguessingapp.ui.theme.LightOrange
import com.example.wordguessingapp.viewmodel.GameViewModel
import com.example.wordguessingapp.ui.theme.boldHeadlineLarge
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun mainScreen(modifier: Modifier, gameViewModel: GameViewModel) {
    var curRow by remember { mutableStateOf(0) }
    val MAX_ROWS = 5
    val MAX_LETTERS = 5

    val endMessage by remember { mutableStateOf(gameViewModel.endMessage) }
    var showDialog by remember { mutableStateOf(false) }

    var isGuessed = remember { gameViewModel.endMessage.length > 0 }
    
    LaunchedEffect(isGuessed) {
        if(endMessage.isNotEmpty()) {
            showDialog = true
            Log.d("SOLUTION", "ye")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Box(
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                    "GUESS THE WORD",
                    style = boldHeadlineLarge,
                    modifier = Modifier
                        .padding(top = 40.dp, start = 60.dp),
                )}
                        },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {

            for (rowIndex in 0 until MAX_ROWS) {
                Row(

                ) {
                    for (letterIndex in 0 until MAX_LETTERS) {
                        val char = gameViewModel.curWord.value.getOrNull(letterIndex)
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .border(2.dp, Color.Black)
                                .size(65.dp),
                            contentAlignment = Alignment.Center,

                            ) {
                            Text(
                                text = if (rowIndex == gameViewModel.currentRow) {
                                    char?.toString() ?: ""
                                } else if (rowIndex < gameViewModel.currentRow) {
                                    gameViewModel.guessList[rowIndex][letterIndex].toString()
                                } else {
                                    ""
                                },
                                style = boldHeadlineLarge
                            )
                        }
                    }
                }
            }

            /* ADD LATER
            Button(
                /* IMPLEMENTATION MISSING, PROVIDED EXAMPLE IS JUST FOR TESTING PURPOSES */
                onClick = { gameViewModel.curWord.value = gameViewModel.solution.value },
                modifier = Modifier
                    .padding(bottom = 30.dp, top = 30.dp),
                shape = CutCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    LightOrange
                )
            ) {
                Text(
                    text = "HINT",
                    style = boldHeadlineLarge
                )
            }

            Row(

            ) {
                Button(
                    onClick = {
                        gameViewModel.generateWord()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightOrange
                    ),
                    shape = CutCornerShape(0.dp),
                    modifier = Modifier
                        .padding(15.dp),
                    enabled = true
                ) {
                    Text(
                        text = "NEW WORD?",
                        style = boldHeadlineLarge
                    )
                }
            }

             */

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(10) { btnIndex ->
                    Button(
                        onClick = { gameViewModel.addLetter(firstRow[btnIndex]) },
                        shape = CutCornerShape(0.dp),
                        modifier = Modifier
                            .padding(2.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "${firstRow[btnIndex]}",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                repeat(9) { btnIndex ->
                    Button(
                        onClick = { gameViewModel.addLetter(secondRow[btnIndex]) },
                        shape = CutCornerShape(0.dp),
                        modifier = Modifier
                            .padding(2.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "${secondRow[btnIndex]}",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Row(

            ) {
                Button(
                    onClick = { gameViewModel.check() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    shape = CutCornerShape(0.dp),
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(3f),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "ENTER",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                repeat(6) { btnIndex ->
                    Button(
                        onClick = { gameViewModel.addLetter(thirdRow[btnIndex]) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray
                        ),
                        shape = CutCornerShape(0.dp),
                        modifier = Modifier
                            .padding(2.dp)
                            .weight(2f),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "${thirdRow[btnIndex]}",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Button(
                    onClick = { gameViewModel.removeLetter() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    shape = CutCornerShape(0.dp),
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .weight(2f),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "<<",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if(showDialog) {
                EndDialog(gameViewModel)
                Log.d("SOLUTION", "yes")
            }

        }
    }
}