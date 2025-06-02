package com.example.jastip.ui.screen.keranjang

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.jastip.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jastip.domain.model.Keranjang

@Composable
fun KeranjangScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: KeranjangViewModel = hiltViewModel()
) {
    // Ambil state dari ViewModel
    val state by viewModel.state.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<Keranjang?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
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
                    text = "Keranjang",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )

            }
            Divider(color = Color.Gray, thickness = 1.dp)
        }


        Spacer(modifier = Modifier.height(25.dp))


        // Daftar item keranjang dari state.items
        state.items.forEach { item ->
            var isChecked by remember { mutableStateOf(false) }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp))
                    .padding(8.dp)
                    .padding(bottom = 8.dp)
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors(checkedColor = Color.Black)
                )

                Spacer(modifier = Modifier.width(8.dp))

                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = "Gambar menu",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = item.name,
                            color = Color.Black,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.sampah),
                            contentDescription = "Hapus Item",
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    itemToDelete = item
                                    showDeleteDialog = true
                                }
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Rp${item.price}",
                        color = Color(0xFFEC5228),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Kurangi",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    if (item.quantity > 1) {
                                        viewModel.updateQuantity(item.id, item.quantity - 1)
                                    }
                                }
                        )
                        Text(
                            text = item.quantity.toString(),
                            color = Color.Black,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Tambah",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    viewModel.updateQuantity(item.id, item.quantity + 1)
                                }
                        )
                    }
                }
            }
        }
        if (showDeleteDialog && itemToDelete != null) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Konfirmasi Hapus") },
                text = { Text("Apakah kamu yakin ingin menghapus item ini dari keranjang?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.removeItem(itemToDelete!!)
                        showDeleteDialog = false
                    }) {
                        Text("Hapus")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text("Batal")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewKeranjangScreen() {
    val navController = rememberNavController()
    KeranjangScreen(navController = navController)
}
