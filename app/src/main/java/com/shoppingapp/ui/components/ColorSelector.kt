package com.shoppingapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@Composable
fun ColorSelector(
    onClick: (() -> Unit)? = null
) {
    val colors = listOf(
        Color(0xFFD6B08C),
        Color.Black,
        Color(0xFFE85D5D)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Row(horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingSmall)) {
        colors.forEachIndexed { index, color ->

            val isSelected = index == selectedIndex

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(Dimens.SizeSelector)
                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .clickable { selectedIndex = index }
            ) {

                if (isSelected) {
                    Box(modifier = Modifier
                            .size(50.dp)
                            .border(
                                width = 2.dp,
                                color = CustomColor.PrimaryColor,
                                shape = CircleShape)
                            .clickable { if (onClick != null) { onClick() } }
                    )
                }

                Box(modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }
    }
}