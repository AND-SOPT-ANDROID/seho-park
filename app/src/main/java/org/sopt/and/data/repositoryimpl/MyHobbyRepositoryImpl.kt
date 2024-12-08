package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.MyHobbyDataSource
import org.sopt.and.data.mapper.Mapper
import org.sopt.and.domain.model.MyHobbyEntity
import org.sopt.and.domain.repository.MyHobbyRepository

class MyHobbyRepositoryImpl(
    private val getMyHobbyDataSource: MyHobbyDataSource
) : MyHobbyRepository {
    override suspend fun getMyHobby(): Result<MyHobbyEntity> =
        runCatching {
            getMyHobbyDataSource.getMyHobby().result?.let { Mapper.toMyHobbyEntity(it) }!!
        }
}