package com.example.data.error

import com.example.data.model.ErrorMessage
import com.example.data.response.ErrorResponse
import com.google.gson.Gson

// mapping errorResponse to ErrorMessage model
fun ErrorResponse.toDomain(code: Int): ErrorMessage {
    return ErrorMessage(
        errorCode = code,
        errorMessage = errorMessage.orEmpty(),
        errorFieldList = errorFieldList ?: emptyList(),
    )
}

// create default error response

fun getDefaultErrorResponse() = ErrorResponse("", "", emptyList())

// getting error response from error body "string"

fun getErrorResponse(gson: Gson, errorBodyString: String): ErrorResponse =
    try {
        gson.fromJson(errorBodyString, ErrorResponse::class.java)
    } catch (e: Exception) {
        getDefaultErrorResponse()
    }
