package com.example.wordguessingapp.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordguessingapp.ui.theme.boldHeadlineLarge
import com.example.wordguessingapp.viewmodel.GameViewModel

@Composable
fun EndDialog(
    gameViewModel: GameViewModel,
    onDismiss: () -> Unit
) {
    val title = gameViewModel.endMessage
    val solvedOrMissed = gameViewModel.solved.value
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    gameViewModel.generateWord()
                    onDismiss
                }
            ) {
                Text(
                    text = "NEW WORD?",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )
            }
        },
        title = { Text(
            text = title,
            fontSize = 20.sp
        ) },
//        dismissButton = {
//            TextButton(
//                onClick = {
//                    onDismiss
//                }
//            ) {
//                Text(
//                    text = "CLOSE",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//        },
        containerColor = Color.White,
        shape = CutCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
    )
}