package com.example.jastip.ui.components.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jastip.R
import com.example.jastip.domain.model.Keranjang
import com.example.jastip.utils.formatDoubleKeRupiah

@Composable
fun KeranjangItem(
    item: Keranjang,
    onDeleteClick: () -> Unit,
    onQuantityChange: (Int) -> Unit,
    isSelected: Boolean, // Parameter baru
    onSelectionChange: (Boolean) -> Unit, // Parameter baru
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { checked -> onSelectionChange(checked) },
            colors = CheckboxDefaults.colors(checkedColor = Color.Black)
        )

        Spacer(Modifier.width(8.dp))

        AsyncImage(
            model = item.imageUrl,
            contentDescription = "Product Image",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Icon(
                    painter = painterResource(id = R.drawable.sampah),
                    contentDescription = "Delete",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(onClick = onDeleteClick),
                    tint = Color.Gray
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Rp${formatDoubleKeRupiah(item.price)}",
                color = Color(0xFFEC5228),
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Decrease",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            if (item.quantity > 1) {
                                onQuantityChange(item.quantity - 1)
                            }
                        },
                    tint = Color.Black
                )

                Text(
                    text = item.quantity.toString(),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            onQuantityChange(item.quantity + 1)
                        },
                    tint = Color.Black
                )
            }
        }
    }
}