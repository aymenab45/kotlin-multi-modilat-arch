package com.example.login.domain.usecase

import com.example.domain.result.OutCome
import com.example.domain.usecase.AsyncUseCase
import com.example.login.data.source.LoginRemote
import com.example.login.domain.model.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(val loginRemote: LoginRemote) : AsyncUseCase<LoginUseCase.Input, User>() {
    override suspend fun run(input: Input): OutCome<User> {
        return loginRemote.login(input.username, input.password)
    }

    data class Input(val username: String, val password: String)
}
