package org.sopt.and.domain.repository

import org.sopt.and.data.datasource.GetMyHobbyDataSource
import org.sopt.and.data.repositoryimpl.GetMyHobbyRepositoryImpl
import org.sopt.and.data.service.ServicePool
import org.sopt.and.domain.model.MyHobbyEntity

interface MyHobbyRepository {
    suspend fun getMyHobby(): Result<MyHobbyEntity>

    companion object {
        fun create(): GetMyHobbyRepositoryImpl {
            return GetMyHobbyRepositoryImpl(
                GetMyHobbyDataSource(
                    ServicePool.userService
                )
            )
        }
    }
}