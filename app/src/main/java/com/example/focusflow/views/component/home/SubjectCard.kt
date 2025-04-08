package com.example.focusflow.views.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focusflow.data.Subject
import com.example.focusflow.ui.theme.CoolGreyBlue
import com.example.focusflow.ui.theme.DarkCharcoal
import com.example.focusflow.ui.theme.ErrorRed
import com.example.focusflow.ui.theme.SkyBlue
import com.example.focusflow.ui.theme.amber
import com.example.focusflow.viewmodel.HomeViewModel

@Composable
fun SubjectCard(
    subject: Subject,
    uid: String?,
    homeViewModel: HomeViewModel
) {
    var showConfirmDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.size(344.dp, 180.dp)
        ) {
            // Shadow layer
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(x = 8.dp, y = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(SkyBlue)
            )

            // Main card
            Card(
                modifier = Modifier
                    .size(344.dp, 180.dp)
                    .defaultMinSize(minHeight = 140.dp),
//                    .shadow(
//                        elevation = 8.dp,
//                        shape = RoundedCornerShape(16.dp),
//                        ambientColor = Color.Red,
//                        spotColor = Color.Red
//                    ),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = amber)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = subject.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            color = DarkCharcoal,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Total notes",
                            fontSize = 16.sp,
                            color = CoolGreyBlue
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete subject",
                            tint = ErrorRed,
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    showConfirmDialog = true
                                }
                        )
                    }
                }
            }
        }
    }

    // Confirmation dialog
    if (showConfirmDialog) {
        ConfirmDelete(
            onCancel = { showConfirmDialog = false },
            onDelete = {
                showConfirmDialog = false
                uid?.let {
                    homeViewModel.deleteSubject(it, subject.name)
                }
            }
        )
    }
}