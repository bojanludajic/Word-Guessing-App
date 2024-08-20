package com.example.wordguessingapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wordguessingapp.data.words
import com.example.wordguessingapp.ui.theme.WordGuessingAppTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordGuessingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    mainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun mainScreen(modifier: Modifier) {
    val random = Random.nextInt(0, words.size)
    val word = words[random]
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { btnIndex ->
                Button(
                    onClick = ({ /* TODO */ }),
                    shape = CutCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(50.dp)
                ) {

                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { btnIndex ->
                Button(
                    onClick = ({ /* TODO */ }),
                    shape = CutCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(50.dp)
                ) {

                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { btnIndex ->
                Button(
                    onClick = ({ /* TODO */ }),
                    shape = CutCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(50.dp)
                ) {

                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { btnIndex ->
                Button(
                    onClick = ({ /* TODO */ }),
                    shape = CutCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(50.dp)
                ) {

                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { btnIndex ->
                Button(
                    onClick = ({ /* TODO */ }),
                    shape = CutCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(50.dp),
                    enabled = false
                ) {
                    Text("${word[btnIndex]}")
                }
            }
        }


        val firstRow = listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P")
        val secondRow = listOf("A", "S", "D", "F", "G", "H", "J", "K", "L")
        val thirdRow = listOf("Z", "C", "V", "B", "N", "M", "<<")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(10) { btnIndex ->
                Button(
                    onClick = { /*TODO*/ },
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
                        text = "${firstRow[btnIndex]}")
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
                    onClick = { /*TODO*/ },
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
                    )
                }
            }
        }
        Row(

        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray
                ),
                shape = CutCornerShape(0.dp),
                modifier = Modifier
                    .padding(2.dp)
                    .weight(3f),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = "ENTER")
            }

            repeat(7) { btnIndex ->
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    ),
                    shape = CutCornerShape(0.dp),
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(2f),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "${thirdRow[btnIndex]}")
                }
            }
        }
    }
}