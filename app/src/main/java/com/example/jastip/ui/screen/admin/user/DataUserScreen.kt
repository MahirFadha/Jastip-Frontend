package com.example.jastip.ui.screen.admin.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jastip.ui.components.admin.TemplateUser

data class UserData(
    val name: String,
    val nim: String,
    val phone: String,
    val role: String
)

@Composable
fun DataUserScreen(navController: NavController, modifier: Modifier = Modifier) {
    val userList = listOf(
        UserData("Adeliafhr", "0904567", "08123456", "User"),
        UserData("Amalia", "0904567", "08123456", "User")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(Color(0xFF3F7D58)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Data Pengguna",
                color = Color.White,
                fontSize = 28.sp
            )
        }

        // Daftar User
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            userList.forEach { user ->
                TemplateUser(user)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewUserScreen() {
    val navController = rememberNavController()
    DataUserScreen(navController)
}
