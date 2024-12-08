package org.sopt.and.presentation.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavigationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NavigationUiState())
    val uiState: StateFlow<NavigationUiState> = _uiState.asStateFlow()

    fun changeBottomNavigationVisibility() {
        _uiState.value = _uiState.value.copy(
            isBottomNavigationVisible = !_uiState.value.isBottomNavigationVisible
        )
    }

    fun setNavigationSelectedIndex(index: Int) {
        _uiState.value = _uiState.value.copy(
            navigationSelectedIndex = index
        )
    }
}