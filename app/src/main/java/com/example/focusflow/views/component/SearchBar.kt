package com.example.focusflow.views.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focusflow.ui.theme.DarkCharcoal
import com.example.focusflow.ui.theme.WarningAmber

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    placeholderText: String = "Search subject",
    onSearch: (String) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = query,
            onValueChange = {
                query = it
                onSearch(it)
            },
            placeholder = {
                Text(
                    text = placeholderText,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
            },
//            leadingIcon = {
//                Icon(Icons.Default.Search, contentDescription = "Search", tint = SkyBlue)
//            },
            trailingIcon = {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(WarningAmber), // Or any color you like
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add new subject",
                        tint = DarkCharcoal,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = DarkCharcoal,
                unfocusedTextColor = DarkCharcoal,
                containerColor = Color(0xFFE0F7FA), // Soft Mint
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
    }
}
