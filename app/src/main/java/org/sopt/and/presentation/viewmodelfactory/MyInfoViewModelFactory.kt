package org.sopt.and.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.and.domain.repository.MyHobbyRepository
import org.sopt.and.domain.usecase.MyHobbyUseCase
import org.sopt.and.presentation.myinfo.MyInfoViewModel

class MyInfoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {

            MyInfoViewModel::class.java -> {
                MyInfoViewModel(
                    MyHobbyUseCase(
                        getMyHobbyRepository = MyHobbyRepository.create()
                    )
                ) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}