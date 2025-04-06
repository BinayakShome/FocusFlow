package com.example.focusflow.views.component.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focusflow.ui.theme.BlazingOrange
import com.example.focusflow.ui.theme.DarkCharcoal
import com.example.focusflow.ui.theme.ErrorRed
import com.example.focusflow.ui.theme.SoftPeach
import com.example.focusflow.ui.theme.SuccessGreen

@Composable
fun AddSubjectDialog(
    onCancel: () -> Unit,
    onCreate: (String) -> Unit
) {
    var subjectName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onCancel,
        containerColor = SoftPeach,
        shape = RoundedCornerShape(24.dp),
        title = {
            Text(
                text = "Add New Subject",
                fontWeight = FontWeight.Bold,
                color = DarkCharcoal,
                fontSize = 20.sp
            )
        },
        text = {
            Column {
                Text(
                    text = "Enter the subject name",
                    color = DarkCharcoal,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = subjectName,
                    onValueChange = { subjectName = it },
                    placeholder = { Text("Subject Name", color = Color.Gray) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BlazingOrange,
                        unfocusedBorderColor = Color.LightGray,
                        cursorColor = BlazingOrange,
                        focusedLabelColor = DarkCharcoal,
                        unfocusedLabelColor = Color.Gray,
                        focusedTextColor = DarkCharcoal,
                        unfocusedTextColor = DarkCharcoal,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (subjectName.isNotBlank()) {
                        onCreate(subjectName.trim())
                    }
                }
            ) {
                Text("Create", color = SuccessGreen, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Cancel", color = ErrorRed)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddSubjectDialogPreview() {
    AddSubjectDialog(
        onCancel = {},
        onCreate = {}
    )
}