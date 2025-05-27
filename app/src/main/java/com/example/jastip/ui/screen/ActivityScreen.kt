package com.example.cobaproject.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cobaproject.ui.components.PesananHeader


@Composable
fun ActivityScreen(navController: NavController,
                   modifier: Modifier = Modifier) {
    var selectedTab = remember { mutableStateOf("Pesanan") }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        PesananHeader(
            selectedTab = selectedTab.value,
            onTabSelected = { tab -> selectedTab.value = tab }
        )

        Spacer(modifier = Modifier.height(24.dp))

//        // Konten berdasarkan tab
//        when (selectedTab.value) {
//            "Pesanan" -> Text("Halaman Pesanan", modifier = Modifier.padding(16.dp))
//            "Riwayat" -> Text("Halaman Riwayat", modifier = Modifier.padding(16.dp))
//        }

//        Spacer(modifier = Modifier.weight(1f)) // Dorong bottom bar ke bawah
//        BottomNavigationBar(selectedItem = "aktivitas", navController = navController)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewActivityScreenAsli() {
    val navController = rememberNavController()
    ActivityScreen(navController = navController)
}

