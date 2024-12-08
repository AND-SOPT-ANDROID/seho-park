package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.MyHobbyEntity
import org.sopt.and.domain.repository.GetMyHobbyRepository

class MyHobbyUseCase(
    private val getMyHobbyRepository: GetMyHobbyRepository
) {
    suspend operator fun invoke(): Result<MyHobbyEntity> =
        getMyHobbyRepository.getMyHobby()
}