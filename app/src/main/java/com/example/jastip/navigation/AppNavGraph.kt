package com.example.jastip.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cobaproject.ui.components.MainScreen
import com.example.cobaproject.ui.screen.*
import com.example.jastip.ui.screen.loginscreen.LoginScreen
import com.example.jastip.ui.screen.register.RegisterScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "signUp",
        modifier = Modifier
    ) {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("signUp") {
            RegisterScreen(navController = navController)
        }
        composable("main") {
            MainScreen(parentNavController = navController)
        }

        // Halaman bukan bagian dari bottom nav
        composable("pagi") {
            PagiScreen(navController = navController)
        }
        composable("siang") {
            SiangScreen(navController = navController)
        }
        composable("sore") {
            SoreScreen(navController = navController)
        }
        composable("edit_profile") {
            EditPfScreen(navController = navController)
        }
        composable("keranjang") {
            KeranjangScreen(navController = navController)
        }
    }
}


