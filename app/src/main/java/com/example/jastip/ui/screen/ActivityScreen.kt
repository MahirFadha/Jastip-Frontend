package com.example.cobaproject.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jastip.R
import androidx.compose.foundation.border
import androidx.compose.material3.ButtonDefaults


@Composable
fun ActivityScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
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

        // Konten pesanan
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .border(1.dp, Color(0xFFBEBEBE), shape = RoundedCornerShape(16.dp))
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                // Baris ID dan status
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("ID64849", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Sedang diproses", color = Color(0xFFEF9651), fontSize = 13.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Alamat",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = "Jl. Asrama Putra No.95, Pisangan,\nKecamatan Wetan, Surabaya.",
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                PesananItem(
                    imageRes = R.drawable.geprek,
                    title = "KFC 1 Paket",
                    price = "Rp. 30.000",
                    quantity = 1
                )

                Spacer(modifier = Modifier.height(12.dp))

                PesananItem(
                    imageRes = R.drawable.geprek,
                    title = "Coca Cola",
                    price = "Rp. 25.000",
                    quantity = 1
                )

                Spacer(modifier = Modifier.height(24.dp))

                HargaItem(label = "Harga Pesanan", value = "Rp. 55.000")
                HargaItem(label = "Biaya Pengiriman", value = "Rp. 5.000")
                HargaItem(label = "Total Harga", value = "Rp. 60.000", bold = true)

                Spacer(modifier = Modifier.height(20.dp))

                // Tombol batal pesanan
                androidx.compose.material3.Button(
                    onClick = { /* aksi batal pesanan */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD51818), // merah tua
                        contentColor = Color.White         // warna teks
                    )
                ) {
                    Text("Batal Pesanan")
                }
            }
        }
    }
}

@Composable
fun PesananItem(imageRes: Int, title: String, price: String, quantity: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .padding(end = 12.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(price, fontSize = 13.sp)
        }

        Text("x$quantity", fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Composable
fun HargaItem(label: String, value: String, bold: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            fontSize = 14.sp,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            value,
            fontSize = 14.sp,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewActivityScreenAsli() {
    val navController = rememberNavController()
    ActivityScreen(navController = navController)
}
