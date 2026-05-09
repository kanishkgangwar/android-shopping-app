package com.shoppingapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shoppingapp.ui.theme.Dimens

@Composable
fun DrawerItem(
    burgerIcon: Int,
    burgerPageName: String,
    isSelected: Boolean,
    navController: NavHostController,
    path: String
) {
    Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                if(isSelected) Color(0xFF7DAACB)
                else Color.Transparent
            )
            .clickable { navController.navigate(path) }
    ) {
        Row(modifier = Modifier.padding(Dimens.PaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = burgerIcon),
                contentDescription = "")
            Spacer(modifier = Modifier.width(Dimens.PaddingSmall))
            Text(burgerPageName)
        }
    }
}