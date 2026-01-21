package com.example.login.data.mapper

import com.example.login.data.response.UserResponse
import com.example.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginMapperImplementer(private val dispatcher: CoroutineDispatcher) : LoginMapper {
    override suspend fun map(response: UserResponse): User {
        return withContext(dispatcher) {
            User(
                id = response.userId.orEmpty(),
                userName = response.userName.orEmpty(),
                email = response.email.orEmpty(),
                accessToken = response.accessToken.orEmpty(),
                refreshToken = response.refreshToken.orEmpty(),
            )
        }
    }
}
