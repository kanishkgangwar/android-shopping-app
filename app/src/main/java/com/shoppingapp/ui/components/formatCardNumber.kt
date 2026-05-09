package com.shoppingapp.ui.components

fun formatCardNumberMasked(cardNumber: String): String {
    val clean = cardNumber.filter { it.isDigit() }
    val masked = clean.mapIndexed { index, c ->
        if (index < clean.length - 4) '*' else c
    }.joinToString("")

    return masked.chunked(4).joinToString(" ")
}