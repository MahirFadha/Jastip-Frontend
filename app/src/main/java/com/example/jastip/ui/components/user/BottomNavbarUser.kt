package com.example.cobaproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.jastip.R
import com.example.jastip.ui.components.BottomNavItem

@Composable
fun BottomNavigationBarUser(
    selectedItem: String,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3F7D58))
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            icon = R.drawable.beranda,
            label = "Beranda",
            isSelected = selectedItem == "beranda",
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate("beranda") }
        )
        BottomNavItem(
            icon = R.drawable.order,
            label = "Aktivitas",
            isSelected = selectedItem == "aktivitas",
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate("aktivitas") }
        )
        BottomNavItem(
            icon = R.drawable.round_account_circle_24,
            label = "Akun",
            isSelected = selectedItem == "akun",
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate("akun") }
        )
    }
}

