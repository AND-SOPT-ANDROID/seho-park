package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.MyHobbyEntity
import org.sopt.and.domain.repository.MyHobbyRepository

class MyHobbyUseCase(
    private val getMyHobbyRepository: MyHobbyRepository
) {
    suspend operator fun invoke(): Result<MyHobbyEntity> =
        getMyHobbyRepository.getMyHobby()
}