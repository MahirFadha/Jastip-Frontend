package com.example.jastip.ui.components.user

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cobaproject.ui.components.BottomNavigationBarUser
import com.example.cobaproject.ui.screen.BerandaScreen
import com.example.jastip.ui.screen.user.activity.ActivityScreen
import com.example.jastip.ui.screen.user.akun.AkunScreen

@Composable
fun NavBarUser(parentNavController: NavHostController) {
    val bottomNavController = rememberNavController() // controller khusus untuk bottom nav
    val currentBackStack = bottomNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack.value?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigationBarUser(
                selectedItem = currentRoute ?: "",
                navController = bottomNavController
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = "beranda", // langsung ke beranda sesuai keinginanmu
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("beranda") {
                BerandaScreen(navController = parentNavController)
            }
            composable("akun") {
                AkunScreen(navController = parentNavController)
            }
            composable("aktivitas") {
                ActivityScreen(navController = parentNavController)
            }
        }
    }
}