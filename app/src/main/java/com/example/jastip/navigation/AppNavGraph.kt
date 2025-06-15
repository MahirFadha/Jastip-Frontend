package com.example.jastip.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cobaproject.ui.screen.*
import com.example.jastip.ui.components.admin.NavBarAdmin
import com.example.jastip.ui.components.user.NavBarUser
import com.example.jastip.ui.screen.admin.menu.MenuScreen
import com.example.jastip.ui.screen.admin.PembatalanScreen
import com.example.jastip.ui.screen.admin.pesanan.PesananScreen
import com.example.jastip.ui.screen.user.keranjang.KeranjangScreen
import com.example.jastip.ui.screen.user.loginscreen.LoginScreen
import com.example.jastip.ui.screen.user.pagi.PagiScreen
import com.example.jastip.ui.screen.user.register.RegisterScreen
import com.example.jastip.utils.CekLogin

@Composable
fun AppNavGraph(navController: NavHostController,start:String) {
    NavHost(
        navController = navController,
        startDestination = start,
        modifier = Modifier
    ) {
//        User
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("signUp") {
            RegisterScreen(navController = navController)
        }
        composable("user") {
            NavBarUser(parentNavController = navController)
        }
        composable("pagi") {
            PagiScreen(navController = navController)
        }
        composable("siang") {
            SiangScreen(navController = navController)
        }
        composable("sore") {
            SoreScreen(navController = navController)
        }
        composable("keranjang") {
            KeranjangScreen(navController = navController)
        }

//        Admin
        composable ("admin"){
            NavBarAdmin(parentNavController = navController)
        }
        composable("menu"){
            MenuScreen(navController = navController)
        }
        composable("pesanan"){
            PesananScreen(navController = navController)
        }
        composable("pembatalan"){
            PembatalanScreen(navController = navController)
        }
        composable("ceklogin"){
            CekLogin(navController = navController)
        }
    }
}


