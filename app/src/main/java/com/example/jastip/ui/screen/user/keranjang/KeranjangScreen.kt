package com.example.jastip.ui.screen.user.keranjang

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jastip.domain.model.Keranjang
import com.example.jastip.ui.components.KeranjangItem

@Composable
fun KeranjangScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: KeranjangViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<Keranjang?>(null) }

    Column(modifier = modifier.fillMaxSize()) {
        // Header Section
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
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

            // Select All Checkbox (Optional)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.selectedItems.size == state.items.size && state.items.isNotEmpty(),
                    onCheckedChange = { viewModel.toggleSelectAll() },
                    colors = CheckboxDefaults.colors(checkedColor = Color.Black)
                )
                Text(
                    text = "Pilih Semua",
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Divider(color = Color.Gray, thickness = 1.dp)
        }

        // Main Content - Scrollable
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.items) { item ->
                KeranjangItem(
                    item = item,
                    isSelected = state.selectedItems.contains(item.id),
                    onSelectionChange = { isSelected ->
                        viewModel.setItemSelection(item.id, isSelected)
                    },
                    onDeleteClick = {
                        itemToDelete = item
                        showDeleteDialog = true
                    },
                    onQuantityChange = { newQuantity ->
                        viewModel.updateQuantity(item.id, newQuantity)
                    }
                )
            }
        }

        // Footer Section (Sticky Bottom)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White)
        ) {
            // Tambahkan Harga Kurir
            if (state.selectedItems.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Biaya Pengiriman", fontWeight = FontWeight.Normal)
                    Text(
                        "Rp${viewModel.getOngkir()}",
                        color = Color.Gray
                    ) // Tambahkan fungsi getOngkir() di ViewModel
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total Harga", fontWeight = FontWeight.Bold)
                Text(
                    "Rp${viewModel.getSelectedItemsTotal()}",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.order()
                    navController.navigate("main")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F7D58)),
                enabled = state.selectedItems.isNotEmpty()
            ) {
                Text("Checkout (${state.selectedItems.size})", color = Color.White)
            }
        }

        if (state.isOrdering) {
            Dialog(onDismissRequest = {}) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            // Delete Confirmation Dialog
            if (showDeleteDialog && itemToDelete != null) {
                AlertDialog(
                    onDismissRequest = { showDeleteDialog = false },
                    title = { Text("Konfirmasi Hapus") },
                    text = { Text("Apakah kamu yakin ingin menghapus item ini?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                itemToDelete?.let { viewModel.removeItem(it) }
                                showDeleteDialog = false
                            }
                        ) {
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewKeranjangScreen() {
    val navController = rememberNavController()
    KeranjangScreen(navController = navController)
}
