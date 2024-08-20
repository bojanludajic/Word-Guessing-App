package com.example.wordguessingapp.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wordguessingapp.data.firstRow
import com.example.wordguessingapp.data.secondRow
import com.example.wordguessingapp.data.thirdRow
import com.example.wordguessingapp.ui.theme.GameViewModel
import com.example.wordguessingapp.ui.theme.LightOrange

@Composable
fun keyboard(gameViewModel: GameViewModel) {
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
                    color = Color.Black
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
                    color = Color.Black
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
                color = Color.Black
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
                    color = Color.Black
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
                color = Color.Black
            )
        }
    }

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
        enabled = false
    ) {
        Text(
            text = "New word?",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
