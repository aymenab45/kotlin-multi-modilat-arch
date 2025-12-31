package com.example.login.data.source

import com.example.data.result.OutCome
import com.example.login.data.requests.LoginRequestBody
import com.example.login.domain.model.User

interface LoginRemote {
    suspend fun login(request: LoginRequestBody): OutCome<User>
}
