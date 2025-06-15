package com.example.jastip.ui.screen.user.loginscreen

import android.content.Context.MODE_PRIVATE
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
import androidx.core.content.edit
import com.example.jastip.data.local.TokenManager
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var nim by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val loginState = viewModel.loginState
    val isFormValid = nim.isNotBlank() && password.isNotBlank()
    val tokenManager = remember { TokenManager(context) }

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

        Text("Login", fontSize = 25.sp)

        Spacer(modifier = Modifier.height(30.dp))

        // nimInput
        OutlinedTextField(
            value = nim,
            onValueChange = { nim = it },
            label = { Text("Nim") },
            placeholder = { Text("Masukkan nim", color = Color.Gray) },
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

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
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

        fun saveUserData(name: String, password: String,nim: String,nomorHp: String){
            val sharedPreferences = context.getSharedPreferences("user_data", MODE_PRIVATE)
            sharedPreferences.edit {
                putString("userName", name)
                putString("userNim", nim)
                putString("userNomor", nomorHp)
                putString("userPassword", password)
            }
        }

        when (loginState) {
            is LoginState.Loading -> {
                CircularProgressIndicator()
            }

            is LoginState.Success -> {
                val name = loginState.user.name
                val nomorHp = loginState.user.nomorHp
                if (nim.isNotEmpty()) {
                    saveUserData(name, password, nim, nomorHp)
                }
                LaunchedEffect(Unit) {
                    when (viewModel.userRole) {
                        "admin" -> navController.navigate("admin")
                        "user" -> navController.navigate("user")
                    }
                }
            }

            is LoginState.Error -> {
                val error = loginState.error
                Text(text = error, color = Color.Red)
            }

            LoginState.Idle -> { /* Do nothing */ }
        }
        // Tombol Daftar
        Button(
            onClick = {
                if (nim.isEmpty()){
                    println("Error")
                } else{
                    viewModel.login(nim,password)
                }},
            enabled = isFormValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F7D58L))
        ) {
            Text(text = "Login", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(
                text = "Belum punya akun?",
                fontSize = 12.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Daftar Sekarang",
                fontSize = 12.sp,
                color = Color.Blue,
                modifier = Modifier.clickable {
                    navController.navigate("SignUp")
                }
            )
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController() // dummy controller
    LoginScreen(navController = navController)
}
