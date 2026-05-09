package com.shoppingapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.shoppingapp.ui.theme.Dimens

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun RecommendedItem(
    productImage: String,
    productName: String,
    productPrice: String
) {
    Row {
        AsyncImage(
            model = productImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .aspectRatio(0.8f)
                .clip(RoundedCornerShape(Dimens.ImageCornerSmall)))
        Spacer(modifier = Modifier.width(Dimens.PaddingSmall))

        Column {
            Text(productName,
                fontSize = Dimens.TextMedium,
                fontWeight = FontWeight(600),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                letterSpacing = 1.2.sp
            )
            Spacer(modifier = Modifier.height(Dimens.PaddingMinor))
            Text(productPrice,
                fontSize = Dimens.TextLarge,
                fontWeight = FontWeight(500),
                letterSpacing = 1.2.sp)
        }
    }
}