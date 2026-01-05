package com.example.data.mapper

import com.example.data.response.ErrorResponse
import com.example.domain.model.ErrorMessage

// mapping errorResponse to ErrorMessage model
fun ErrorResponse.toDomain(code: Int): ErrorMessage {
    return ErrorMessage(
        errorCode = code,
        errorMessage = errorMessage.orEmpty(),
        errorFieldList = errorFieldList ?: emptyList(),
    )
}
