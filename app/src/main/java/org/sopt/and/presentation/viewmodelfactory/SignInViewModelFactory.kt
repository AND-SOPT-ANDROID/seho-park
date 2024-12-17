package org.sopt.and.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.and.domain.repository.SignInRepository
import org.sopt.and.domain.usecase.SignInUseCase
import org.sopt.and.presentation.signin.SignInViewModel

class SignInViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {

            SignInViewModel::class.java -> {
                SignInViewModel(
                    SignInUseCase(
                        signInRepository = SignInRepository.create()
                    )
                ) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}