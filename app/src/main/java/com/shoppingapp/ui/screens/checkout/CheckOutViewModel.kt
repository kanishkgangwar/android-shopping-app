package com.shoppingapp.ui.screens.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppingapp.data.Repository
import com.shoppingapp.data.local.entity.CreditCardEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    fun addCreditCard(creditCardEntity: CreditCardEntity) {
        viewModelScope.launch {
            repository.addCreditCard(creditCardEntity)
        }
    }

    fun deleteCreditCard(id: Long) {
        viewModelScope.launch {
            repository.deleteCreditCard(id)
        }
    }

    val savedCreditCard = repository.getAllCreditCard()
}