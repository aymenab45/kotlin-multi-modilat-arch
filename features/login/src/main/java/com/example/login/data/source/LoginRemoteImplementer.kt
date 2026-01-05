package com.example.login.data.source

import com.example.data.mapper.toDomain
import com.example.domain.result.OutCome
import com.example.data.source.NetworkDataSource
import com.example.login.data.mapper.LoginMapper
import com.example.login.data.requests.LoginRequestBody
import com.example.login.data.service.LoginService
import com.example.login.domain.model.User

class LoginRemoteImplementer(private val networkDataSource: NetworkDataSource<LoginService>, private val loginMapper: LoginMapper) : LoginRemote {

    override suspend fun login(username:String, password:String): OutCome<User> {
        return networkDataSource.performRequest(
            request = { login(LoginRequestBody(username, password)) },
            onSuccess = { response, _ -> OutCome.success(loginMapper.map(response)) },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) },
        )
    }
}
