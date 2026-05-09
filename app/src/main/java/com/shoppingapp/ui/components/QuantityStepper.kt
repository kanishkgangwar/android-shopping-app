package com.shoppingapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shoppingapp.ui.theme.Dimens

@Composable
fun QuantityStepper(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(modifier = Modifier
            .height(Dimens.PaddingHuge)
            .clip(RoundedCornerShape(50))
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = Dimens.PaddingSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "-",
            fontSize = Dimens.TextLarge,
            color = Color.Black,
            modifier = Modifier
                .clickable { onDecrease() }
                .padding(8.dp)
        )

        Text(text = "$quantity",
            fontSize = Dimens.TextLarge,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(text = "+",
            fontSize = Dimens.TextLarge,
            color = Color.Black,
            modifier = Modifier
                .clickable { onIncrease() }
                .padding(8.dp)
        )
    }
}