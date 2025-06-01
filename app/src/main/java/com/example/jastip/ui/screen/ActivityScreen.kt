package com.example.cobaproject.ui.screen

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cobaproject.ui.components.PesananHeader


@Composable
fun ActivityScreen(navController: NavController,
                   modifier: Modifier = Modifier) {
    var selectedTab = remember { mutableStateOf("Pesanan") }

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(25.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(Color(0xFF3F7D58)),
//                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pesanan",
                    color = Color.White,
                    fontSize = 28.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxHeight()
//                    .padding(12.dp, 20.dp, 12.dp, 0.dp)
            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.pesanan),
//                    modifier = Modifier
//                    .fillMaxWidth(),
//            }


//        PesananHeader(
//            selectedTab = selectedTab.value,
//            onTabSelected = { tab -> selectedTab.value = tab }
//        )

//            Spacer(modifier = Modifier.height(24.dp))

//        // Konten berdasarkan tab
//        when (selectedTab.value) {
//            "Pesanan" -> Text("Halaman Pesanan", modifier = Modifier.padding(16.dp))
//            "Riwayat" -> Text("Halaman Riwayat", modifier = Modifier.padding(16.dp))
//        }

//        Spacer(modifier = Modifier.weight(1f)) // Dorong bottom bar ke bawah
//        BottomNavigationBar(selectedItem = "aktivitas", navController = navController)
            }
        }

    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewActivityScreenAsli() {
//    val navController = rememberNavController()
//    ActivityScreen(navController = navController)
//}

