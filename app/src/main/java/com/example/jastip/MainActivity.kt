package com.example.jastip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.jastip.data.local.TokenManager
import com.example.jastip.navigation.AppNavGraph
import com.example.jastip.ui.theme.JastipTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
//        enableEdgeToEdge()

        val tokenManager = TokenManager(this)
        val startDestination = when {
            tokenManager.getUser() != null &&
                    tokenManager.getToken() != null &&
                    tokenManager.cekLogin() -> {
                if (tokenManager.getUser()?.role == "admin") "admin" else "user"
            }
            else -> "login"
        }

        setContent {
            JastipTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController,startDestination)
            }
        }
    }
}
