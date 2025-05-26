package com.example.jastip.ui.screen.akun

import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraEnhance
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jastip.R
import com.example.jastip.domain.model.User


@Composable
fun AkunScreen(
    navController: NavController,
    viewModel: AkunViewModel = hiltViewModel(),
    ) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_data", MODE_PRIVATE)
//    var nama = sharedPreferences.getString("userName","namaUser")?:"namaUser"
//    var password = sharedPreferences.getString("userPassword","passwordUser")?:"passwordUser"
    val savedName = sharedPreferences.getString("userName", null)
    val savedPassword = sharedPreferences.getString("userPassword", null)
    val savedNim = sharedPreferences.getLong("userNim", -1)
    var passwordVisible by remember { mutableStateOf(false) }
    val akunState = viewModel.akunState

    LaunchedEffect(Unit) {
        if (savedName != null && savedPassword != null && savedNim != -1L){
        val user = User(name = savedName, nim = savedNim, password = savedPassword)
        viewModel.setUser(user)
            }
    }

    val nama by viewModel::name
    val password by viewModel::password

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Picture
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_account_circle_24),
                contentDescription = "Profile Picture",
                tint = Color.Gray,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )

            Icon(
                imageVector = Icons.Default.CameraEnhance,
                contentDescription = "Edit Icon",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 2.dp, y = 2.dp)
                    .size(28.dp)
                    .background(Color.Gray, CircleShape)
                    .padding(2.dp),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        // Nama
        OutlinedTextField(
            value = nama,
            onValueChange = { viewModel.name = it },
            label = { Text("Nama") },
            placeholder = { Text("Masukkan nama") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.round_assignment_ind_24),
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(350.dp)
                .height(60.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(15.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.password = it },
            label = { Text("Password") },
            placeholder = { Text("Masukkan password", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.lock),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(25.dp) // Atur ukuran ikon di sini
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.eye),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(25.dp) // Ukuran ikon trailing
                        .clickable { passwordVisible = !passwordVisible }
                )
            },
            visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(350.dp)
                .height(60.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        LaunchedEffect(akunState) {
            when (akunState) {
                is AkunState.Success -> {
                    Toast.makeText(context, (akunState.message), LENGTH_SHORT).show()
                    val editor = sharedPreferences.edit()
                    editor.putString("userName", viewModel.name)
                    editor.apply()
                }

                is AkunState.Error -> {
                    Toast.makeText(context, (akunState.error), LENGTH_SHORT).show()
                }

                else -> { /* do nothing */
                }
            }
        }

        // Tombol Simpan Perubahan
        Button(
            onClick = {
                viewModel.edit()
//                println("Nama: $nama, Password: $password")
//                navController.popBackStack()
            },
            modifier = Modifier
                .width(300.dp) // Lebar custom
                .height(100.dp) // Tinggi custom
                .align(Alignment.CenterHorizontally) // Posisikan tombol di tengah
                .padding(horizontal = 32.dp, vertical = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3F7D58),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Simpan Perubahan",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Tombol Logout
        Button(
            onClick = {
                navController.navigate("login") {
                    popUpTo(0) // Hapus semua backstack
                }
            },
            modifier = Modifier
                .width(300.dp) // Lebar custom
                .height(100.dp) // Tinggi custom
                .align(Alignment.CenterHorizontally) // Posisikan tombol di tengah
                .padding(horizontal = 32.dp, vertical = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEF9651),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Logout",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAkunScreen() {
    val navController = rememberNavController()
//    AkunScreen(navController = navController)
}
