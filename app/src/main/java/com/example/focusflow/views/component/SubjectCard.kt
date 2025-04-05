package com.example.focusflow.views.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focusflow.ui.theme.AquaBlue
import com.example.focusflow.ui.theme.BabyBlue
import com.example.focusflow.ui.theme.CoolGreyBlue
import com.example.focusflow.ui.theme.CreamyYellow
import com.example.focusflow.ui.theme.DarkCharcoal
import com.example.focusflow.ui.theme.ErrorRed
import com.example.focusflow.ui.theme.LavenderBlush
import com.example.focusflow.ui.theme.LightCoral
import com.example.focusflow.ui.theme.LightYellow
import com.example.focusflow.ui.theme.PaleLime
import com.example.focusflow.ui.theme.PastelMint
import com.example.focusflow.ui.theme.PowderBlue
import com.example.focusflow.ui.theme.SkyBlue
import com.example.focusflow.ui.theme.SoftLilac
import com.example.focusflow.ui.theme.SoftPeach
import com.example.focusflow.ui.theme.WarningAmber

@Preview
@Composable
fun SubjectCard() {
    // Random background color
    val colorsList = listOf(
        SkyBlue, LightYellow, WarningAmber, AquaBlue, SoftLilac, CreamyYellow,
        PaleLime, PowderBlue, SoftPeach, PastelMint, LavenderBlush, BabyBlue, LightCoral
    )
    val backgroundColor = remember { colorsList.random() }

    Box(
        modifier = Modifier
            .fillMaxSize()
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
                    .background(Color.Cyan.copy(alpha = 0.3f))
            )

            // Main card
            Card(
                modifier = Modifier
                    .size(344.dp, 180.dp) // Match the shadow box size
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = Color.Red,
                        spotColor = Color.Red
                    ),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = backgroundColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "DSA",
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp,
                            color = DarkCharcoal
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Last studied",
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
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}
