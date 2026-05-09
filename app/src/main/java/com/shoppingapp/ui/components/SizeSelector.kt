package com.shoppingapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@Composable
fun SizeSelector() {
    val sizes = listOf("S", "M", "L")
    var selectedIndex by remember { mutableStateOf(2) }

    Row(horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingSmall)) {
        sizes.forEachIndexed { index, size ->

            val isSelected = index == selectedIndex

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(Dimens.SizeSelector)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) CustomColor.PrimaryColor
                        else CustomColor.AlternateColor)
                    .clickable { selectedIndex = index }
            ) {
                Text(text = size,
                    color = if (isSelected) Color.White else Color.Gray,
                    fontSize = Dimens.TextMedium
                )
            }
        }
    }
}