package com.example.jastip.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.jastip.data.local.TokenManager
import kotlinx.coroutines.delay

@Composable
fun CekLogin(navController: NavController){
    val context = LocalContext.current
    val tokenManager = remember { TokenManager(context) }

    LaunchedEffect(Unit) {


    }
}