package com.example.cobaproject.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jastip.R
import com.example.jastip.ui.screen.pagi.PagiViewModel
import androidx.compose.material3.Text
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter


@Composable
fun PagiScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PagiViewModel = hiltViewModel()
) {
    val state by viewModel.state

    // Load data dari database lokal sekali saat pertama kali screen muncul
    LaunchedEffect(Unit) {
        viewModel.reload() // ✅ Ini fungsi yang tersedia di ViewModel
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Header
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
                        .padding(start = 16.dp)
                        .size(24.dp)
                        .clickable { navController.popBackStack() }
                )
                Text(
                    text = "PAGI",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Divider(color = Color.Gray, thickness = 1.dp)
        }

        var searchText by remember { mutableStateOf("") }
        var selectedCategory by remember { mutableStateOf("All") }
        val categoryOptions = listOf("All", "Food", "Drink")
        var expanded by remember { mutableStateOf(false) }

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

            Spacer(modifier = Modifier.width(8.dp))

            Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
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

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    // TODO: filter menu berdasarkan searchText dan selectedCategory jika ingin
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

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn {
                items(state.menuList) { menu ->
                    Log.d("ImageLoad", "Loading image URL: '${menu.imageUrl.trim()}'")
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = menu.imageUrl.trim()),
                            contentDescription = menu.name,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(menu.name, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                            Text("Rp${menu.price}", fontSize = 14.sp, color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.keranjang),
                            contentDescription = "Keranjang",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPagiScreen() {
    val navController = rememberNavController()
    PagiScreen(navController = navController)
}