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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jastip.domain.model.pesananDiproses.GrupPesananDiproses
import com.example.jastip.domain.model.pesananDiproses.PesananDiproses
import com.example.jastip.utils.formatDoubleKeRupiah

@Composable
fun TemplatePesananDiproses(
    pesanan: GrupPesananDiproses,
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
            Text(text = pesanan.nama, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = pesanan.status, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = pesanan.idPesanan, color = Color(0xFF1565C0), fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row Inti
        pesanan.detail.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.Top
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row {
                        Text(text = item.menuName, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = item.sesi, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                    Text(text = item.category, fontSize = 12.sp, color = Color.Gray)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "x${item.jumlah}", fontSize = 14.sp)
                    Text(text = "Rp${formatDoubleKeRupiah(item.hargaItem)}", fontSize = 14.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row Bawah (Total dan Ongkir)
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Kurir: Rp5000", fontSize = 14.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Total: Rp${formatDoubleKeRupiah(pesanan.totalHarga)}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}