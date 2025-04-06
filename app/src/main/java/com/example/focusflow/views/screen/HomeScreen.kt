package com.example.focusflow.views.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.focusflow.ui.theme.DarkCharcoal
import com.example.focusflow.ui.theme.amber
import com.example.focusflow.viewmodel.HomeViewModel
import com.example.focusflow.views.component.home.AddSubjectDialog
import com.example.focusflow.views.component.misc.NoInternet
import com.example.focusflow.views.component.home.SearchBar
import com.example.focusflow.views.component.home.SubjectCard
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val context = LocalContext.current
    val showNoInternet by viewModel.showNoInternet.collectAsState()

    val firebaseUser = FirebaseAuth.getInstance().currentUser
    val uid = firebaseUser?.uid
    val userName = firebaseUser?.displayName ?: "User"

    var showDialog by remember { mutableStateOf(false) }

    val subjectList by viewModel.subjects.collectAsState()

    LaunchedEffect(uid) {
        viewModel.checkInternetAvailability(context)
        uid?.let { viewModel.fetchSubjects(it) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Hello, $userName 👋",
                        color = DarkCharcoal,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate("ProfileScreen") }) {
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
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                SearchBar(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    placeholderText = "Search subject",
                    onSearch = {},
                    onAddClick = { showDialog = true }
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                ) {
                    if (showNoInternet) {
                        item {
                            NoInternet()
                        }
                    } else {
                        items(subjectList) { subject ->
                            SubjectCard(subjectName = subject.name)
                        }
                    }
                }
            }

            if (showDialog) {
                AddSubjectDialog(
                    onCancel = { showDialog = false },
                    onCreate = { subjectName ->
                        showDialog = false
                        uid?.let {
                            viewModel.addSubject(it, subjectName)
                        }
                    }
                )
            }
        }
    }
}
