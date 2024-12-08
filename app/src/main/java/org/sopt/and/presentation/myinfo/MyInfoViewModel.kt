package org.sopt.and.presentation.myinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.model.MyHobbyEntity
import org.sopt.and.domain.usecase.MyHobbyUseCase

class MyInfoViewModel(
    private val getMyHobbyUseCase: MyHobbyUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyInfoUiState())
    val uiState: StateFlow<MyInfoUiState> = _uiState.asStateFlow()

    private fun setMyHobby(myHobby: String) {
        _uiState.value = _uiState.value.copy(myHobby = myHobby)
    }

    fun getMyHobby() {
        viewModelScope.launch {
            getMyHobbyUseCase().onSuccess { myHobbyEntity: MyHobbyEntity ->
                setMyHobby(myHobbyEntity.myHobby)
            }.onFailure { }
        }
    }
}