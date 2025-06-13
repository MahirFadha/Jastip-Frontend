package com.example.jastip.ui.screen.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
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

@Composable
fun berandaAdminScreen(navController: NavController, modifier: Modifier = Modifier) {
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
            MenuItem("Kategori Jasa", icon = "\uD83D\uDCC4")
            MenuItem("Jumlah Pesanan", icon = "\uD83D\uDCCB")
            MenuItem("Pesanan Dibatalkan", icon = "\u274C")
        }
    }
}

@Composable
fun MenuItem(title: String, icon: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp) // tinggi kotak menu diperbesar
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
            .clickable { }
            .padding(horizontal = 16.dp), // padding kiri-kanan
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = icon, fontSize = 26.sp, modifier = Modifier.padding(end = 12.dp))
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Medium)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBerandaAdmin() {
    val navController = rememberNavController()
    berandaAdminScreen(navController)
}
