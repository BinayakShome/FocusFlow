package com.example.focusflow.views.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.focusflow.ui.theme.CoolGreyBlue
import com.example.focusflow.ui.theme.DarkCharcoal
import com.example.focusflow.ui.theme.ErrorRed
import com.example.focusflow.ui.theme.LightYellow
import com.example.focusflow.ui.theme.amber
import com.example.focusflow.views.component.NoInternet
import com.example.focusflow.views.component.NoSubject
import com.example.focusflow.views.component.SearchBar
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val firebaseUser = FirebaseAuth.getInstance().currentUser
    val userName = firebaseUser?.displayName
        ?.substringBefore('_')
        ?.substringBefore(' ')
        ?: "User"

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Hello, $userName \uD83D\uDC4B",
                            color = DarkCharcoal,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate("//TODO//") }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Profile",
                                tint = DarkCharcoal,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = amber)
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(4.dp))
                    SearchBar(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        placeholderText = "Search subject",
                        onSearch = {}
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
