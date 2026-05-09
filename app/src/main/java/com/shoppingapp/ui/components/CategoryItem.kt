package com.shoppingapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shoppingapp.ui.screens.CategoryItemState
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@Composable
fun CategoryItem(
    icon: Int,
    label: String,
    category: CategoryItemState,
    selectedCategory: CategoryItemState,
    onClick: (CategoryItemState) -> Unit
) {
    val isSelected = category == selectedCategory

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(80.dp)
                .clickable { onClick(category) }
        ) {
            if(isSelected) {
                Box(modifier = Modifier
                        .size(80.dp)
                        .border(2.dp, CustomColor.PrimaryColor, CircleShape)
                )
                Box(modifier = Modifier
                        .size(70.dp)
                        .background(CustomColor.PrimaryColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = Color.White)
                }
            }
            else {
                Box(modifier = Modifier
                    .size(70.dp)
                    .background(CustomColor.AlternateColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = Color.Gray)
                }
            }
        }
        Spacer(modifier = Modifier.height(Dimens.PaddingSmall))
        Text(label,
            letterSpacing = 0.4.sp)
    }
}