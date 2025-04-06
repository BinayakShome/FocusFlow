package com.example.focusflow.views.component.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focusflow.ui.theme.DarkCharcoal

@Composable
fun DataCard(
    topic: String,
    modifier: Modifier,
    color: Color
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(color),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = topic,
                fontSize = 16.sp,
                color = DarkCharcoal,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Divider( // cleaner divider than HorizontalDivider
                modifier = Modifier
                    .width(80.dp)
                    .height(2.dp)
                    .background(DarkCharcoal)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "06", // you can replace with actual data
                fontSize = 16.sp,
                color = DarkCharcoal
            )
        }
    }
}
