package org.sopt.and.data.datasource

import org.sopt.and.data.model.response.MyHobbyResponseDto
import org.sopt.and.data.service.UserService

class MyHobbyDataSource(
    private val userService: UserService
) {
    suspend fun getMyHobby(): MyHobbyResponseDto = userService.getMyHobby()
}