package com.shoppingapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@Composable
fun PaymentMethod(
    icon: Int,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(Dimens.CardsCornerSmall),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) CustomColor.PrimaryColor
            else CustomColor.AlternateColor),
        elevation = CardDefaults.cardElevation(Dimens.CardElevation)
    ) {
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PaddingMedium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(Dimens.IconMedium)
            )

            Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

            Text(text = title,
                color = Color.Black)
        }
    }
}