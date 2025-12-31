package com.example.login.data.source

import com.example.data.error.toDomain
import com.example.data.result.OutCome
import com.example.data.source.NetworkDataSource
import com.example.login.data.requests.LoginRequestBody
import com.example.login.data.service.LoginService
import com.example.login.domain.mapper.LoginMapper
import com.example.login.domain.model.User

class LoginRemoteImplementer(private val networkDataSource: NetworkDataSource<LoginService>, private val loginMapper: LoginMapper) : LoginRemote {

    override suspend fun login(request: LoginRequestBody): OutCome<User> {
        return networkDataSource.performRequest(
            request = { login(request) },
            onSuccess = { response, _ -> OutCome.success(loginMapper.map(response)) },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) },
        )
    }
}
