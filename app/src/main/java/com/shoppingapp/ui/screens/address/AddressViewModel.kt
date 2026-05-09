package com.shoppingapp.ui.screens.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppingapp.data.Repository
import com.shoppingapp.data.local.entity.DeliveryAddressEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    fun addNewAddress(newAddress: DeliveryAddressEntity) {
        viewModelScope.launch {
            repository.addNewAddress(newAddress)
        }
    }

    fun updateAddress(id: Long, placeTitle: String, placeAddress: String) {
        viewModelScope.launch {
            repository.updateAddress(id, placeTitle, placeAddress)
        }
    }

    val savedAddress = repository.getAllAddress()

    fun deleteAddressById(id: Long) {
        viewModelScope.launch {
            repository.deleteAddressById(id)
        }
    }
}