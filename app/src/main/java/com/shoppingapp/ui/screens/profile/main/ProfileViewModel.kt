package com.shoppingapp.ui.screens.profile.main

import androidx.lifecycle.ViewModel
import com.shoppingapp.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class MenuItem(
    val icon: Int,
    val title: String,
    val path: String
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

}