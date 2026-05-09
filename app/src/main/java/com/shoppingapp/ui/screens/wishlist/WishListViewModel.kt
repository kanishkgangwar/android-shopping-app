package com.shoppingapp.ui.screens.wishlist

import androidx.lifecycle.ViewModel
import com.shoppingapp.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

}