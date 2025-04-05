package com.example.focusflow

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.focusflow.navigation.AppNavigation
import com.example.focusflow.ui.theme.FocusFlowTheme
import com.example.focusflow.viewmodel.LoginViewModel
import com.example.focusflow.views.component.SubjectBox
import com.google.firebase.FirebaseApp
import javax.security.auth.Subject

class MainActivity : ComponentActivity() {
    private lateinit var googleAuthViewModel: LoginViewModel
    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            googleAuthViewModel.handleSignInResult(result.data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()

        googleAuthViewModel = LoginViewModel().apply {
            initGoogleSignIn(this@MainActivity)
        }

        setContent {
            FocusFlowTheme() {
                AppNavigation()
            }
        }
    }
}