package org.sopt.and.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.and.domain.repository.SignUpRepository
import org.sopt.and.domain.usecase.SignUpUseCase
import org.sopt.and.presentation.signup.SignUpViewModel

class SignUpViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {

            SignUpViewModel::class.java -> {
                SignUpViewModel(
                    SignUpUseCase(
                        signUpRepository = SignUpRepository.create()
                    )
                ) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}