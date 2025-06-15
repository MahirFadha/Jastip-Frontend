package com.example.jastip.ui.screen.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jastip.R

@Composable
fun ProfilAdminScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top App Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFF3F7D58)), // Orange
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Profile",
                fontSize = 30.sp,
                color = Color.White
            )
        }

        // Foto Profil (ditengah dan diberi jarak dari atas)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.round_account_circle_24),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
        }

        // Info Profil
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            ProfileItem(icon = Icons.Default.Person, text = "John Doe")
            Divider()
            ProfileItem(icon = Icons.Default.Phone, text = "+1 2345 6789")
            Divider()
            PasswordItem(icon = Icons.Default.Lock, password = "password123")
            Divider()
        }
    }
}

@Composable
fun ProfileItem(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 16.sp, color = Color.Black)
    }
}

@Composable
fun PasswordItem(icon: ImageVector, password: String) {
    var isVisible by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = if (isVisible) password else "********",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = if (isVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
            contentDescription = "Toggle Password Visibility",
            tint = Color.Gray,
            modifier = Modifier
                .size(24.dp)
                .clickable { isVisible = !isVisible }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfilAdmin() {
    val navController = rememberNavController()
    ProfilAdminScreen(navController)
}
