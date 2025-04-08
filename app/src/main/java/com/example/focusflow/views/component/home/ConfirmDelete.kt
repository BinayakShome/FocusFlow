package com.example.focusflow.views.component.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focusflow.ui.theme.DarkCharcoal
import com.example.focusflow.ui.theme.ErrorRed
import com.example.focusflow.ui.theme.LightYellow
import com.example.focusflow.ui.theme.SoftLilac

@Composable
fun ConfirmDelete(
    onCancel: () -> Unit,
    onDelete: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = {
            Text(
                text = "Confirm Deletion",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = DarkCharcoal
            )
        },
        text = {
            Text(
                text = "Do you really want to delete the subject? Deleted subjects can't be revived.",
                fontSize = 16.sp,
                color = DarkCharcoal
            )
        },
        confirmButton = {
            TextButton(
                onClick = onDelete
            ) {
                Text(
                    text = "Delete",
                    color = ErrorRed,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onCancel
            ) {
                Text(
                    text = "Cancel",
                    color = ErrorRed
                )
            }
        },
        containerColor = LightYellow,
        shape = RoundedCornerShape(16.dp)
    )
}