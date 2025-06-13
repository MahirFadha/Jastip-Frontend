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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jastip.R
import com.example.jastip.data.local.TokenManager
import com.example.jastip.domain.model.riwayatPesanan.GrupRiwayatPesanan
import com.example.jastip.domain.model.riwayatPesanan.RiwayatPesanan
import com.example.jastip.ui.screen.user.activity.ActivityViewModel
import com.example.jastip.utils.formatDoubleKeRupiah
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.tan

@Composable
fun PesananTemplate(pesanan: GrupRiwayatPesanan) {
    val context = LocalContext.current
    val viewmodel: ActivityViewModel = hiltViewModel()
    val tokenManager = remember{TokenManager(context)}
    val nim = tokenManager.getUser()?.nim?:""
    var notifDialog by remember { mutableStateOf(false) }
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
                Text("${pesanan.idPesanan}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(pesanan.status, color = Color(0xFFEF9651), fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            val formatterTanggal = SimpleDateFormat("EEEE, dd-MM-yyyy HH:mm", Locale("id", "ID"))
            val tanggal = try {
                val tanggalPesanan = Date(pesanan.waktuPesanan.toLong())
                formatterTanggal.format(tanggalPesanan)
            }catch (e: Exception){
                "Tanggal tidak valid"
            }

            // Simulasi alamat (sementara hardcode)
            Text("Waktu Pemesanan", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(
                tanggal,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Item menu (bisa ada lebih dari 1 nantinya)
            pesanan.pesanan.forEach {
                PesananItem(
                    imageRes = R.drawable.geprek,
                    title = it.name,
                    price = "Rp.${formatDoubleKeRupiah(it.hargaItem)}",
                    quantity = it.jumlah,
                    sesi = it.sesi
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Harga simulasi (kalau belum fix dari backend)
            val totalHarga = pesanan.pesanan.sumOf { it.hargaItem * it.jumlah } + 5000
            HargaItem(label = "Harga Pesanan", value = "Rp. ${formatDoubleKeRupiah(pesanan.pesanan.sumOf { it.hargaItem * it.jumlah }) }")
            HargaItem(label = "Biaya Pengiriman", value = "Rp. 5.000")
            HargaItem(label = "Total Harga", value = "Rp. ${formatDoubleKeRupiah(totalHarga)}", bold = true)

            Spacer(modifier = Modifier.height(20.dp))

            if (pesanan.status == "Diproses") {
                Button(
                    onClick = { notifDialog = true },
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

            if (notifDialog == true){
                AlertDialog(
                    onDismissRequest = {notifDialog = false},
                    title = { Text("Batal Pesanan") },
                    text = { Text("Apakah kamu yakin ingin membatalkan pesanan ini?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                viewmodel.batalkanPesanan(pesanan.idPesanan, nim)
                                notifDialog = false
                            }
                        ) {
                            Text("Hapus")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                notifDialog = false
                            }
                        ) {
                            Text("Batal")
                        }
                    }
                )
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