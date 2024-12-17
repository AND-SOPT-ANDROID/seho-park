package org.sopt.and.core.utils

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    object Empty : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    object Failure : UiState<Nothing>()
}