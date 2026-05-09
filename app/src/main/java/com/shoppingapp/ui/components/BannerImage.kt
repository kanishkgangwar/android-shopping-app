package com.shoppingapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.shoppingapp.ui.theme.Dimens

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun BannerImage(
    imageUrl: String
) {
    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 8f) // 🔥 key fix
                .clip(RoundedCornerShape(Dimens.ImageCornerSmall)),
            contentScale = ContentScale.Crop
        )
    }
}