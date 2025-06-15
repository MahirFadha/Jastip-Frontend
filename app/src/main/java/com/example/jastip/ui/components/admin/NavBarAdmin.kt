package com.example.jastip.ui.components.admin

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jastip.ui.screen.admin.beranda.BerandaAdminScreen
import com.example.jastip.ui.screen.admin.user.DataUserScreen
import com.example.jastip.ui.screen.admin.ProfilAdminScreen

@Composable
fun NavBarAdmin(parentNavController: NavHostController){
    val bottomNavController = rememberNavController()
    val currentBackStach = bottomNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStach.value?.destination?.route

    Scaffold (
        bottomBar = {
            BottomNavigationBarAdmin(
                selectedItem = currentRoute ?: "",
                navControler = bottomNavController
            )
        }
    ){ innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = "beranda",
            modifier = Modifier.padding(innerPadding)
        ){
            composable("beranda"){
                BerandaAdminScreen(navController = parentNavController)
            }
            composable ("pengguna"){
                DataUserScreen(navController = parentNavController)
            }
            composable ("akun"){
                ProfilAdminScreen(navController = parentNavController)
            }
        }

    }
}