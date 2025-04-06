package com.example.focusflow.views.component.misc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focusflow.ui.theme.ElectricPink

@Preview
@Composable
fun BottomSignature() {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(top = 32.dp), // Add spacer effect via padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "@Binayak",
                modifier = Modifier.clickable {
                    uriHandler.openUri("https://www.linkedin.com/in/binayak-shome-831b192b6/?originalSubdomain=in")
                },
                color = ElectricPink,
                fontSize = 15.sp
            )
            Text(" | ", color = ElectricPink, fontSize = 13.sp)
            Text(
                "Contact",
                modifier = Modifier.clickable {
                    uriHandler.openUri("https://wa.me/+918812989114")
                },
                color = ElectricPink,
                fontSize = 15.sp
            )
            Text(" | ", color = ElectricPink, fontSize = 13.sp)
            Text(
                "Gmail",
                modifier = Modifier.clickable {
                    uriHandler.openUri("mailto:binayakshome3@gmail.com")
                },
                color = ElectricPink,
                fontSize = 15.sp
            )
            Text(" | ", color = ElectricPink, fontSize = 13.sp)
            Text(
                "Instagram",
                modifier = Modifier.clickable {
                    uriHandler.openUri("https://www.instagram.com/binayakshome_06/")
                },
                color = ElectricPink,
                fontSize = 15.sp
            )
        }
    }
}
