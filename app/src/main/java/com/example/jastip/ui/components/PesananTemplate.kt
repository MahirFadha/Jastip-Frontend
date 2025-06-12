package com.example.jastip.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jastip.R
import com.example.jastip.domain.model.RiwayatPesanan

@Composable
fun PesananTemplate(pesanan: RiwayatPesanan) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFBEBEBE), shape = RoundedCornerShape(16.dp))
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            // ID dan Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(pesanan.idPesanan, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(pesanan.status, color = Color(0xFFEF9651), fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Simulasi alamat (sementara hardcode)
            Text("Alamat", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(
                "Jl. Asrama Putra No.95, Pisangan,\nKecamatan Wetan, Surabaya.",
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Item menu (bisa ada lebih dari 1 nantinya)
            PesananItem(
                imageRes = R.drawable.geprek, // Ganti jika punya image loader URL
                title = pesanan.name,
                sesi = pesanan.sesi,
                price = "Rp. ${pesanan.hargaItem}",
                quantity = pesanan.jumlah
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Harga simulasi (kalau belum fix dari backend)
            HargaItem(label = "Harga Pesanan", value = "Rp. ${pesanan.hargaItem}")
            HargaItem(label = "Biaya Pengiriman", value = "Rp. 5.000")
            HargaItem(label = "Total Harga", value = "Rp. ${pesanan.hargaItem + 5000}", bold = true)

            Spacer(modifier = Modifier.height(20.dp))

            if (pesanan.status == "Diproses") {
                Button(
                    onClick = { /* aksi batal pesanan */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD51818),
                        contentColor = Color.White
                    )
                ) {
                    Text("Batal Pesanan")
                }
            }
        }
    }
}

@Composable
fun PesananItem(imageRes: Int, title: String, price: String, quantity: Int, sesi: String) {
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
            Row(){
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier
                    .width(4.dp))
                Text("(" + sesi + ")", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
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