package com.shoppingapp.ui.screens.entry

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppingapp.data.Repository
import com.shoppingapp.data.local.entity.UserEntity
import com.shoppingapp.ui.screens.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.core.content.edit

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val repository: Repository,
    @ApplicationContext private val context: Context
): ViewModel() {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    private val _authSuccess = MutableStateFlow(false)
    val authSuccess = _authSuccess.asStateFlow()

    private val _error  = MutableStateFlow<String?>(null)
    val error  = _error .asStateFlow()

    fun registerUser(mainViewModel: MainViewModel) {

        if (firstName.isBlank() ||
            email.isBlank() ||
            password.isBlank() ||
            confirmPassword.isBlank()
        ) { _error.value = "All fields are required"
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _error.value = "Invalid email"
            return
        }

        if (password.length < 6) {
            _error.value = "Password must be at least 6 characters"
            return
        }

        if (password != confirmPassword) {
            _error.value = "Password do not match"
            return
        }

        viewModelScope.launch {
            val existingUser = repository.getUserByEmail(email)

            if (existingUser != null) {
                _error.value = "User already exists"
                return@launch
            }

            val user = UserEntity(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password
            )

            repository.registerUser(user)

            mainViewModel.setCurrentUser(user)

            context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                .edit {
                    putBoolean("isLoggedIn", true)
                        .putString("userEmail", user.email)
                }

            _authSuccess.value = true
        }
    }

    fun loginUser(mainViewModel: MainViewModel) {

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _error.value = "Invalid email"
            return
        }

        viewModelScope.launch {
            val user = repository.loginUser(email, password)

            if(user == null) {
                _error.value = "Invalid email or password"
                return@launch
            }

            mainViewModel.setCurrentUser(user)

            context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                .edit {
                    putBoolean("isLoggedIn", true)
                        .putString("userEmail", user.email)
                }

            _authSuccess.value = true
        }
    }
}