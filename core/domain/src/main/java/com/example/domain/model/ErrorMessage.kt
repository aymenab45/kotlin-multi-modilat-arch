package com.example.domain.model

data class ErrorMessage(
    val errorCode: Int,
    val errorMessage: String,
    val errorFieldList: List<String>,

)
