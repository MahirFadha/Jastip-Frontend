package com.example.cobaproject.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jastip.R

@Composable
fun SoreScreen(navController: NavController, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Bar Atas dengan teks di tengah dan ikon kembali di kiri, plus garis bawah
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp) // Pindah padding ke sini
                        .size(24.dp)
                        .clickable { navController.popBackStack() }
                )

                Text(
                    text = "SORE",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth() // Sekarang benar-benar full
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tambahkan di awal sebelum Row
        var searchText by remember { mutableStateOf("") }
        var selectedCategory by remember { mutableStateOf("All") }
        val categoryOptions = listOf("All", "Food", "Drink")
        var expanded by remember { mutableStateOf(false) }

// Search bar layout
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                textStyle = TextStyle(fontSize = 12.sp, color = Color.Black),
                modifier = Modifier
                    .width(170.dp)
                    .height(40.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 10.dp)
            ) { innerTextField ->
                if (searchText.isEmpty()) {
                    Text("Search", color = Color.Gray, fontSize = 12.sp)
                }
                innerTextField()
            }

            // Spacer untuk memberikan jarak antara Search dan Dropdown
            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .wrapContentSize(Alignment.TopStart)
            ) {
                OutlinedButton(
                    onClick = { expanded = true },
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    modifier = Modifier
                        .height(40.dp)
                        .width(70.dp)
                ) {
                    Text(selectedCategory, fontSize = 12.sp)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categoryOptions.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp)) // Jarak sebelum tombol Search

            Button(
                onClick = {
                    // TODO: Tambahkan logika pencarian
                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF001D8A)),
                modifier = Modifier
                    .height(40.dp)
                    .width(90.dp)
            ) {
                Text("Search", color = Color.White, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        // Item Menu
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.geprek),
                contentDescription = "Gambar Geprek Dalang",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f) // Supaya teks ambil ruang sisa
            ) {
                Text("Pentol", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Text("5.000", fontSize = 14.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.width(12.dp))
            // Tambahkan ikon keranjang
            Icon(
                painter = painterResource(id = R.drawable.keranjang), // Ganti dengan resource kamu
                contentDescription = "Keranjang",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSoreScreen() {
    val navController = rememberNavController()
    SoreScreen(navController = navController)
}