package com.shoppingapp.ui.screens.home.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shoppingapp.ui.components.BannerImage
import com.shoppingapp.ui.components.ProductItem
import com.shoppingapp.ui.components.RecommendedItem
import com.shoppingapp.ui.components.SectionHeader
import com.shoppingapp.ui.screens.home.Product
import com.shoppingapp.ui.theme.Dimens

@Composable
fun CategoryContent(
    banner: String,
    featureProducts: List<Product>,
    recommendedProducts: List<Product>,
    listState: LazyListState
) {
    BannerImage("https://i.pinimg.com/736x/5b/ad/64/5bad6467c05a9ded359cd964a5864ec6.jpg")

    SectionHeader("Recommended")

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.ImageGapLargest)
    ) {
        items(recommendedProducts) { item ->
            RecommendedItem(
                productImage = item.image,
                productName = item.name,
                productPrice = item.price
            )
        }
    }

    SectionHeader("Top Collection")

    BannerImage("https://i.pinimg.com/736x/5b/ad/64/5bad6467c05a9ded359cd964a5864ec6.jpg")
    BannerImage("https://i.pinimg.com/736x/26/7a/42/267a429f6edbc66b048677548638a0bb.jpg")
    BannerImage("https://i.pinimg.com/736x/0c/01/51/0c015154e12f315c49b949f0d5a91741.jpg")
}