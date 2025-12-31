package com.example.data.result

import com.example.data.model.ErrorMessage

interface UseCase<R> {
    suspend fun onSuccess(success: OutCome.Success<R>)

    suspend fun onEmpty()

    suspend fun onError(errorMessage: ErrorMessage)
}
