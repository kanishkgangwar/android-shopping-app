package com.shoppingapp.ui.screens.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppingapp.data.Repository
import com.shoppingapp.data.local.entity.RecentSearchEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val allQuery = repository.getAllQuery()

    fun addQuery(query: String) {
        viewModelScope.launch {
            repository.addQuery(RecentSearchEntity(query = query))
        }
    }

    fun deleteQueryById(id: Long) {
        viewModelScope.launch {
            repository.deleteQueryById(id)
        }
    }

    fun deleteAllQueries() {
        viewModelScope.launch {
            repository.deleteAllQueries()
        }
    }
}