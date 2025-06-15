package com.example.jastip.ui.screen.admin.pesanan

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jastip.domain.model.pesananDiproses.GrupPesananDiproses
import com.example.jastip.ui.components.admin.TemplatePesananDiproses

@Composable
fun PesananScreen(navController: NavController,
                  modifier: Modifier = Modifier,
                  viewmodel: PesananViewModel = hiltViewModel()
) {
    val state by viewmodel.state
    var pesanan by remember { mutableStateOf(listOf<GrupPesananDiproses>()) }

    LaunchedEffect(state.pesananList){
        if (state.pesananList.isNotEmpty() && pesanan.isEmpty()){
            pesanan = state.pesananList
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // AppBar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(24.dp)
                    .clickable{navController.navigate("admin")}
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Jumlah Pesanan",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

//        Divider(color = Color.LightGray, thickness = 1.dp)

        // Card Pesanan
        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }else{
            LazyColumn {
                items(pesanan) { pesanan ->
                    TemplatePesananDiproses(pesanan)
                }
            }
        }

        // Divider full width sampai pojok layar
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPesanan() {
    val navController = rememberNavController()
    PesananScreen(navController)
}
