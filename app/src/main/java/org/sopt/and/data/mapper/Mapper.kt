package org.sopt.and.data.mapper

import org.sopt.and.data.model.request.SignInRequestDto
import org.sopt.and.data.model.request.SignUpRequestDto
import org.sopt.and.data.model.response.GetMyHobbyResponseResultDto
import org.sopt.and.data.model.response.SignInResponseDto
import org.sopt.and.data.model.response.SignUpResponseDto
import org.sopt.and.domain.model.MyHobbyEntity
import org.sopt.and.domain.model.SignInInformationEntity
import org.sopt.and.domain.model.SignInResponseEntity
import org.sopt.and.domain.model.SignUpInformationEntity
import org.sopt.and.domain.model.SignUpResponseEntity
import retrofit2.Response

object Mapper {
    fun toMyHobbyEntity(getHobbyResponseResultDto: GetMyHobbyResponseResultDto) =
        MyHobbyEntity(myHobby = getHobbyResponseResultDto.myHobby)

    fun toSignUpResponseEntity(signUpResponseDto: Response<SignUpResponseDto>) =
        signUpResponseDto.body()?.result?.let {
            SignUpResponseEntity(
                no = it.no,
                status = signUpResponseDto.code(),
                code = signUpResponseDto.body()!!.code
            )
        }

    fun toSignInResponseEntity(signInResponseDto: Response<SignInResponseDto>) =
        signInResponseDto.body()?.result?.let {
            SignInResponseEntity(
                token = it.token,
                status = signInResponseDto.code(),
                code = signInResponseDto.body()!!.code
            )
        }

    fun toSignInRequestDto(signInInformationEntity: SignInInformationEntity) = SignInRequestDto(
        username = signInInformationEntity.username,
        password = signInInformationEntity.password
    )

    fun toSignUpRequestDto(signUpInformationEntity: SignUpInformationEntity) = SignUpRequestDto(
        username = signUpInformationEntity.username,
        password = signUpInformationEntity.password,
        hobby = signUpInformationEntity.hobby
    )
}