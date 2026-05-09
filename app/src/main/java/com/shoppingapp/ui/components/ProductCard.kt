package com.shoppingapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.shoppingapp.ui.theme.Dimens

@Composable
fun ProductCard(
    product: ProductUiModel,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.clickable { onClick() }) {
        AsyncImage(model = product.imageUrls.first(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.9f)
                .clip(RoundedCornerShape(Dimens.ImageCornerSmall)))

        Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

        Text(product.title, maxLines = 2)
        Text(product.priceText,
            fontWeight = FontWeight(500)
        )
    }
}