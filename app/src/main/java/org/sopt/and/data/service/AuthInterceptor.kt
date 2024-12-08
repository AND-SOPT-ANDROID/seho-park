package org.sopt.and.data.service

import android.content.Context
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val tokenManager = TokenManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        var token: String?

        runBlocking {
            token = tokenManager.getToken().first()
        }

        val request = chain.request().newBuilder()
            .apply {
                if (token != null) {
                    addHeader(HEADER_NAME, token!!)
                }
            }
            .build()
        return chain.proceed(request)
    }

    companion object {
        const val HEADER_NAME = "token"
    }
}