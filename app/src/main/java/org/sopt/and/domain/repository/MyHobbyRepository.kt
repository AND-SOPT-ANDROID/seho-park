package org.sopt.and.domain.repository

import org.sopt.and.data.datasource.MyHobbyDataSource
import org.sopt.and.data.repositoryimpl.MyHobbyRepositoryImpl
import org.sopt.and.data.service.ServicePool
import org.sopt.and.domain.model.MyHobbyEntity

interface MyHobbyRepository {
    suspend fun getMyHobby(): Result<MyHobbyEntity>

    companion object {
        fun create(): MyHobbyRepositoryImpl {
            return MyHobbyRepositoryImpl(
                MyHobbyDataSource(
                    ServicePool.userService
                )
            )
        }
    }
}