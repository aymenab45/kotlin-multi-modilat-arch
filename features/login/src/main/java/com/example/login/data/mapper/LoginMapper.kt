package com.example.login.data.mapper

import com.example.login.data.response.UserResponse
import com.example.domain.model.User

interface LoginMapper {
    suspend fun map(response: UserResponse): User
}
