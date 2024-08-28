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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordguessingapp.data.firstRow
import com.example.wordguessingapp.data.secondRow
import com.example.wordguessingapp.data.thirdRow
import com.example.wordguessingapp.ui.theme.DarkerGray1
import com.example.wordguessingapp.ui.theme.DarkerGray2
import com.example.wordguessingapp.viewmodel.GameViewModel
import com.example.wordguessingapp.ui.theme.boldHeadlineLarge
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.wordguessingapp.R
import com.example.wordguessingapp.ui.theme.DarkGray2
import com.example.wordguessingapp.ui.theme.DarkGray3
import com.example.wordguessingapp.ui.theme.DarkGray4


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "MutableCollectionMutableState"
)
@Composable
fun MainScreen(
    modifier: Modifier,
    gameViewModel: GameViewModel
) {
    val MAX_ROWS = 5
    val MAX_LETTERS = 5

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val rowColors = gameViewModel.rowColors.value
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    gameViewModel.backGroundColor.value
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "GUESS THE WORD",
                    style = boldHeadlineLarge,
                    color =
                    if(gameViewModel.darkMode.value) {
                        Color.White
                    } else {
                        Color.Black
                    }
                )
                Switch(
                    checked = gameViewModel.darkMode.value,
                    onCheckedChange = { gameViewModel.toggleDarkMode() },
                    colors = SwitchDefaults.colors(
                        checkedIconColor = Color.White,
                        uncheckedIconColor = Color.Black,
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.Black,
                        checkedTrackColor = Color.Black,
                        uncheckedTrackColor = Color.White
                    ),
                    modifier = Modifier
                        .padding(start = 30.dp)
                )
            }
            for (rowIndex in 0 until MAX_ROWS) {
                Row(

                ) {
                    for (letterIndex in 0 until MAX_LETTERS) {
                        val char = gameViewModel.curWord.value.getOrNull(letterIndex)
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .border(
                                    width = 2.dp,
                                    color = if (!gameViewModel.darkMode.value) {
                                        if (rowColors[rowIndex][letterIndex] == Color.White) Color.Black else Color.White
                                    } else {
                                        if (rowColors[rowIndex][letterIndex] == Color.White) DarkGray4 else DarkerGray1
                                    }
                                )
                                .size(65.dp)
                                .background(
                                    if (rowColors[rowIndex][letterIndex] == Color.White && gameViewModel.darkMode.value) {
                                        DarkerGray1
                                    } else if (rowColors[rowIndex][letterIndex] == Color.White && !gameViewModel.darkMode.value) {
                                        Color.White
                                    } else {
                                        rowColors[rowIndex][letterIndex]
                                    }
                                ),
                            contentAlignment = Alignment.Center,
                            ) {
                            Text(
                                text = when {
                                    rowIndex == gameViewModel.currentRow -> char?.toString() ?: ""
                                    rowIndex < gameViewModel.currentRow -> gameViewModel.guessList[rowIndex][letterIndex].toString()
                                    else -> ""
                                },
                                style = boldHeadlineLarge,
                                color = if(rowColors[rowIndex][letterIndex] == Color.White && !gameViewModel.darkMode.value) Color.Black else Color.White
                            )
                        }
                    }
                }
            }


            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                val score = gameViewModel.wins
                val wordCount = gameViewModel.wordCount
                val wins = "$score"
                Text(
                    style = boldHeadlineLarge,
                    text = "WINS: $wins",
                    modifier = Modifier
                        .padding(10.dp),
                    color = if(gameViewModel.darkMode.value) {
                        Color.White
                    } else {
                        Color.Black
                    }
                )

                Text(
                    style = boldHeadlineLarge,
                    text = "WORDS: $wordCount",
                    modifier = Modifier
                        .padding(10.dp),
                    color = if(gameViewModel.darkMode.value) {
                        Color.White
                    } else {
                        Color.Black
                    }
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    repeat(10) { btnIndex ->
                        val char = firstRow.getOrNull(btnIndex) ?: ""
                        val color = gameViewModel.letterColors[char]!!
                        Button(
                            onClick = { gameViewModel.addLetter(firstRow[btnIndex]) },
                            shape = CutCornerShape(0.dp),
                            modifier = Modifier
                                .padding(2.dp)
                                .height(45.dp)
                                .width(33.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if(gameViewModel.darkMode.value) {
                                    if(color == Color.LightGray) DarkGray3 else color
                                } else {
                                    color
                                }
                            ),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = "$char",
                                color = if (gameViewModel.darkMode.value) {
                                    Color.White
                                } else {
                                    if (color == Color.DarkGray) Color.White else Color.Black
                                },
                                fontSize = 25.sp,
                                fontWeight = FontWeight.ExtraBold
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
                        val char = secondRow.getOrNull(btnIndex) ?: ""
                        val color = gameViewModel.letterColors[char]!!
                        Button(
                            onClick = { gameViewModel.addLetter(secondRow[btnIndex]) },
                            shape = CutCornerShape(0.dp),
                            modifier = Modifier
                                .padding(2.dp)
                                .height(45.dp)
                                .width(33.dp),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if(gameViewModel.darkMode.value) {
                                    if(color == Color.LightGray) DarkGray3 else color
                                } else {
                                    color
                                }
                            )
                        ) {
                            Text(
                                text = "$char",
                                color = if (gameViewModel.darkMode.value) {
                                    Color.White
                                } else {
                                    if (color == Color.DarkGray) Color.White else Color.Black
                                },
                                fontSize = 25.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(start = 18.dp)
                ) {
                    repeat(7) { btnIndex ->
                        val char = thirdRow.getOrNull(btnIndex) ?: ""
                        val color = gameViewModel.letterColors[char]!!
                        Button(
                            onClick = { gameViewModel.addLetter(thirdRow[btnIndex]) },
                            shape = CutCornerShape(0.dp),
                            modifier = Modifier
                                .padding(2.dp)
                                .height(45.dp)
                                .width(33.dp),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if(gameViewModel.darkMode.value) {
                                    if(color == Color.LightGray) DarkGray3 else color
                                } else {
                                    color
                                }
                            )
                        ) {
                            Text(
                                text = "$char",
                                color = if (gameViewModel.darkMode.value) {
                                    Color.White
                                } else {
                                    if (color == Color.DarkGray) Color.White else Color.Black
                                },
                                fontSize = 25.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
                    val solved = gameViewModel.solved.value
                    Button(
                        onClick = { if (!solved) gameViewModel.removeLetter() },
                        shape = CutCornerShape(0.dp),
                        modifier = Modifier
                            .padding(horizontal = 2.dp, vertical = 2.dp)
                            .width(60.dp)
                            .height(45.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if(gameViewModel.darkMode.value) {
                                DarkGray3
                            } else {
                                Color.LightGray
                            }
                        )
                    ) {
                        Text(
                            text = "⌫",
                            color = if (gameViewModel.darkMode.value) {
                                Color.White
                            } else {
                                Color.Black
                            },
                            fontSize = 27.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 40.dp)
            ) {
                Button(
                    onClick = {
                        gameViewModel.check()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(gameViewModel.darkMode.value) {
                            DarkGray3
                        } else {
                            Color.LightGray
                        }
                    ),
                    shape = CutCornerShape(0.dp),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(170.dp)
                        .height(70.dp),
                    contentPadding = PaddingValues(0.dp),
                    enabled = (!gameViewModel.solved.value && !gameViewModel.failed.value)
                ) {
                    Text(
                        text = "ENTER",
                        color = if(gameViewModel.darkMode.value) {
                            Color.White
                        } else {
                            Color.Black
                        },
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        gameViewModel.generateWord()
                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .size(70.dp),
                    colors = ButtonDefaults.buttonColors(
                        if(gameViewModel.darkMode.value) {
                            DarkGray3
                        } else {
                            Color.LightGray
                        }
                    )
                ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "New words",
                            modifier = Modifier
                                .size(50.dp),
                            tint = if(gameViewModel.darkMode.value) Color.White else Color.Black
                        )
                }
            }

            if(gameViewModel.solved.value) {
                val endMessage = gameViewModel.endMessage
                Log.d("SOLUTION", "yes")
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = endMessage
                    )
                }
            }

            if(gameViewModel.failed.value) {
                val endMessage = "Almost! Solution: ${gameViewModel.solution.value}"
                scope.launch {
                    snackBarHostState.showSnackbar(
                        endMessage
                    )
                }
            }

            if(gameViewModel.wordTooShort.value == true) {
                scope.launch {
                    snackBarHostState.showSnackbar(
                        "Not enough letters"
                    )
                }
                gameViewModel.wordTooShort.value = false
            }

            if(gameViewModel.wrongWord.value == true) {
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = "Invalid word",
                    )
                }
                gameViewModel.wrongWord.value = false
            }
            
            if(showDialog) {
                SettingsDialog(
                    gameViewModel = gameViewModel
                ) {
                    
                }
            }

        }
    }
}