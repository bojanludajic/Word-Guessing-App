package com.example.wordguessingapp.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.wordguessingapp.viewmodel.GameViewModel

@Composable
fun EndDialog(gameViewModel: GameViewModel) {

    var message by remember { mutableStateOf(gameViewModel.endMessage) }
    var display = message.isNotBlank()

    if(display) {
        AlertDialog(
            onDismissRequest = { display = false },
            title = { Text(message) },
            confirmButton = {
                Button(
                    onClick = {
                        display = false
                    }
                ) {
                    Text("OK")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}