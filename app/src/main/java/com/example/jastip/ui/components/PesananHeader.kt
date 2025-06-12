package com.example.cobaproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController

@Composable
fun PesananHeader(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    val tabs = listOf("Pesanan", "Riwayat")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3F7D58))
            .padding(horizontal = 16.dp),

        horizontalArrangement = Arrangement.SpaceAround
    ) {
        tabs.forEach { tab ->
            Column(
                modifier = Modifier
                    .clickable { onTabSelected(tab) }
                    .padding(vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .width(120.dp),
                    text = tab,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = if (tab == selectedTab) Color(0xFFEF9651
                    ) else Color.White,
                    fontWeight = if (tab == selectedTab) FontWeight.Medium else FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (tab == selectedTab) {
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .width(24.dp)
                            .background(Color(0xFFEF9651))
                    )
                } else {
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderPesananPrev() {
    val navController = rememberNavController()

}
