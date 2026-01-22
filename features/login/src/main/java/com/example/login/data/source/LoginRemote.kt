package com.example.login.data.source

import com.example.domain.model.User
import com.example.domain.result.OutCome

interface LoginRemote {
    suspend fun login(username: String, password: String): OutCome<User>
}
