package com.shoppingapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.shoppingapp.R
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@Composable
fun CartItemCard(
    product: ProductUiModel,
    quantity: Int,
    mainViewModel: MainViewModel
) {
    Card(modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(CustomColor.AlternateColor),
        shape = RoundedCornerShape(Dimens.ButtonCornerLarge)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = product.imageUrls.first(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(Dimens.ImageLargest)
                    .clip(RoundedCornerShape(Dimens.ButtonCornerMedium))
            )
            Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.PaddingMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.PaddingSmall)
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(product.title,
                        fontWeight = FontWeight(500),
                        color = Color.Black)
                    Icon(painter = painterResource(id = R.drawable.delete),
                        contentDescription = "",
                        modifier = Modifier.clickable { mainViewModel.deleteCartItem(product.id) })
                }
                val price = product.price.toString()
                Text("$ $price",
                    fontSize = Dimens.TextLarge,
                    fontWeight = FontWeight(600),
                    color = Color.Black)

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Size: L",
                        color = Color.Black)
                    QuantityStepper(
                        quantity = quantity,
                        onIncrease = { mainViewModel.increaseQuantity(product.id) },
                        onDecrease = { mainViewModel.decreaseQuantity(product.id) }
                    )
                }
            }
        }
    }
}