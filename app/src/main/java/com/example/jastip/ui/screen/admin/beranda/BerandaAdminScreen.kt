package com.example.jastip.ui.screen.admin.beranda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jastip.ui.components.admin.TemplateMenuBeranda

@Composable
fun BerandaAdminScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp), // Padding umum
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bagian header
        Column(
            modifier = Modifier.padding(top = 80.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Selamat Datang", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("Admin", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(35.dp))
        // Bagian menu
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TemplateMenuBeranda("Menu", icon = "\uD83D\uDCC4", onClick = {navController.navigate("menu")})
            TemplateMenuBeranda("Pesanan", icon = "\uD83D\uDCCB", onClick = {navController.navigate("pesanan")})
            TemplateMenuBeranda("Pesanan Dibatalkan", icon = "\u274C", onClick = {navController.navigate("pembatalan")})
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAdmin() {
    val navController = rememberNavController()
    BerandaAdminScreen(navController)
}
