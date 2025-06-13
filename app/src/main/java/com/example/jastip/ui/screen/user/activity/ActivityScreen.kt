package com.example.jastip.ui.screen.user.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.jastip.data.local.TokenManager
import com.example.jastip.ui.components.PesananTemplate
import androidx.compose.foundation.lazy.items
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun ActivityScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewmodel: ActivityViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val listPesanan = viewmodel.riwayatPesanan.value
    val tokenManager = remember { TokenManager(context) }

    LaunchedEffect(Unit) {
        val nim = tokenManager.getUser()?.nim ?: ""
        viewmodel.loadPesanan(nim)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        // Header custom (punyamu)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(Color(0xFF3F7D58)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pesanan",
                color = Color.White,
                fontSize = 28.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn (
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(listPesanan) {pesananGroup ->
                PesananTemplate(pesananGroup)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewActivityScreenAsli() {
    val navController = rememberNavController()
    ActivityScreen(navController = navController)
}
