package com.example.jastip.ui.components.admin

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TemplateMenuBeranda(title: String, icon: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp) // tinggi kotak menu diperbesar
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp), // padding kiri-kanan
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = icon, fontSize = 26.sp, modifier = Modifier.padding(end = 12.dp))
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Medium)
    }
}