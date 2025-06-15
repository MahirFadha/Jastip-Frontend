package com.example.jastip.ui.components.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun TemplatePesananDiproses(
//    name: String,
//    menu: String
//    status: String,
//    idPesanan: Int,
//    jumlah: Int,
//    hargaItem: Double,
//    sesi: String,
//    imageUrl: String
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFF8F8F8), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {

        // Row Atas
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "nama", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "status", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "ID: idPesanan", color = Color(0xFF1565C0), fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row Inti
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = "",
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)) {
                Row {
                Text(text = "Geprek", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.width(6.dp))
                Text(text = "sesi", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Text(text = "Ayam Digeprek", fontSize = 12.sp, color = Color.Gray)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(horizontalAlignment = Alignment.End,
                modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(text = "x1", fontSize = 14.sp)
                Text(text = "Rp 5000", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row Bawah (Total dan Ongkir)
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Kurir: Rp 5000", fontSize = 14.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Total: 10.000",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Previewtemplate(){
    TemplatePesananDiproses()
}