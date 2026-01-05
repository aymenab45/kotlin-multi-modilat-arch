package com.example.login.data.source

import com.example.domain.result.OutCome
import com.example.login.data.requests.LoginRequestBody
import com.example.login.domain.model.User

interface LoginRemote {
    suspend fun login(username:String, password:String): OutCome<User>
}
