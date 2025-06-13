package com.example.jastip.ui.screen.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class UserData(
    val name: String,
    val nim: String,
    val phone: String,
    val role: String
)

@Composable
fun dataUserScreen(navController: NavController, modifier: Modifier = Modifier) {
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
                UserCard(user)
            }
        }
    }
}

@Composable
fun UserCard(user: UserData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(16.dp)
            )
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = user.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "NIM : ${user.nim}",
                fontSize = 12.sp,
                color = Color.DarkGray
            )
            Text(
                text = "No HP : ${user.phone}",
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        }
        Text(
            text = user.role,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDataUserScreen() {
    val navController = rememberNavController()
    dataUserScreen(navController)
}
