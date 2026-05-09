package com.shoppingapp.ui.screens.profile.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppingapp.data.Repository
import com.shoppingapp.data.local.entity.UserEntity
import com.shoppingapp.ui.screens.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSettingsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")
    var gender by mutableStateOf("Male")

    fun loadUser(user: UserEntity) {

        firstName = user.firstName
        lastName = user.lastName
        email = user.email
        phone = user.phone
        gender = user.gender
    }

    fun saveProfile(
        currentUser: UserEntity,
        mainViewModel: MainViewModel
    ) {

        viewModelScope.launch {

            val updatedUser = currentUser.copy(
                firstName = firstName,
                lastName = lastName,
                email = email,
                phone = phone,
                gender = gender
            )

            repository.updateUser(updatedUser)

            mainViewModel.setCurrentUser(updatedUser)
        }
    }
}