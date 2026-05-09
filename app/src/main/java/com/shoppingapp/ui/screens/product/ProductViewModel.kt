package com.shoppingapp.ui.screens.product

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.shoppingapp.data.Repository
import com.shoppingapp.ui.components.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    val currentRating = mutableStateOf("4.9")
    val reviewsNum = mutableStateOf(0)

    var reviews = mutableStateOf(listOf<Review>())
        private set

    fun addReview(review: Review) {
        reviews.value += review
    }
}