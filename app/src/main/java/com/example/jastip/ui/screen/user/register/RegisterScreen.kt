package com.example.jastip.ui.screen.user.register

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jastip.R

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
    ) {

    val name = viewModel.name
    val nim = viewModel.nim
    val password = viewModel.password
    val nomorHp = viewModel.nomorHp
    var passwordVisible by remember { mutableStateOf(false) }
    val isFormValid = name.isNotBlank() && nim.isNotBlank() && password.isNotBlank()
    val context = LocalContext.current
    val registerState = viewModel.registerState


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Selamat Datang di Aplikasi Jastip", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(30.dp))

        // Nama
        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.name = it },
            label = { Text("Nama") },
            placeholder = { Text("Masukkan nama", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.round_account_circle_24),
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            visualTransformation = VisualTransformation.None,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // NIM
        OutlinedTextField(
            value = nim,
            onValueChange = { viewModel.nim = it },
            label = { Text("NIM") },
            placeholder = { Text("Masukkan NIM", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.round_assignment_ind_24),
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        // No Hp
        OutlinedTextField(
            value = nomorHp,
            onValueChange = { viewModel.nomorHp = it },
            label = { Text("Nomor Hp") },
            placeholder = { Text("Masukkan nomor", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.lock),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(25.dp) // Atur ukuran ikon di sini
                )
            },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

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
                .fillMaxWidth()
                .height(60.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        LaunchedEffect(registerState) {
            when (registerState) {
                is RegisterState.Success -> {
                    Toast.makeText(context, (registerState.message), LENGTH_SHORT).show()
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }

                is RegisterState.Error -> {
                    val error = registerState.error
                    Toast.makeText(context, error, LENGTH_SHORT).show()
                }

                else -> { /* do nothing */ }
            }
        }

        // Tombol Daftar
        Button(
            onClick = { viewModel.register() },
            enabled = isFormValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F7D58L)),
        ) {
            Text(text = "Sign Up", color = Color.White)
        }
//        message?.let {
//            val toast = Toast.makeText(context, it, Toast.LENGTH_SHORT)
//            toast.show()
////            Text(text = it, color = Color.Black)
//        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(
                text = "Sudah punya akun?",
                fontSize = 12.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Masuk Sekarang",
                fontSize = 12.sp,
                color = Color.Blue,
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController() // dummy controller
    RegisterScreen(navController = navController)
}



