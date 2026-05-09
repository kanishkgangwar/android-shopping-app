package com.shoppingapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.shoppingapp.ui.theme.Dimens

@Composable
fun ProfileOptionItem(
    icon: Int,
    title: String,
    path: String,
    navController: NavHostController
) {
    Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.PaddingMedium,
                    vertical = Dimens.PaddingLarge)
            .clickable { navController.navigate(path) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = icon),
                contentDescription = "")
            Spacer(modifier = Modifier.width(Dimens.PaddingSmall))
            Text(title)
        }
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "")
    }
}