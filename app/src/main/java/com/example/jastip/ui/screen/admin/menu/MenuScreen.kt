package com.example.jastip.ui.screen.admin.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.jastip.data.local.entity.MenuEntity
import com.example.jastip.ui.components.admin.TemplateMenu
import androidx.compose.foundation.lazy.items


@Composable
fun MenuScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MenuViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {

        val state by viewModel.state
        var menu by remember { mutableStateOf(listOf<MenuEntity>()) }

        LaunchedEffect(state.menuList) {
            if (menu.isEmpty() && state.menuList.isNotEmpty()){
                menu = state.menuList
            }
        }
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
                        .clickable { navController.navigate("admin") }
                )
                Text(
                    text = "Daftar Menu",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Divider(color = Color.Gray, thickness = 1.dp)
        }

        // Konten dengan padding kecil
        Column(modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 0.dp)) {

            // Isi Kartu Menu
            if (state.isLoading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }else{
                LazyColumn {
                    items(menu) { menu ->
                        TemplateMenu(
                            menu.imageUrl,
                            menu.name,
                            menu.price,
                            menu.category
                        )
                    }
                }
            }
        }

        // Divider full layar
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color.LightGray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMenu() {
    val navController = rememberNavController()
    MenuScreen(navController)
}
