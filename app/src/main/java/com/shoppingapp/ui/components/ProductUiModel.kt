package com.shoppingapp.ui.components

import com.shoppingapp.data.remote.Product

data class ProductUiModel(
    val id: Int,
    val title: String,
    val price: Int,
    val priceText: String,
    val imageUrls: List<String>,
    val category: String,
    val description: String
)

fun Product.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = id,
        title = title,
        price = price,
        priceText = "$$price",
        imageUrls = images,
        category = category.name,
        description = description
    )
}