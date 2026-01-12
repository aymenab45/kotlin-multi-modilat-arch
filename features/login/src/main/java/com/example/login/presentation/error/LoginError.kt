package com.example.login.presentation.error

import com.example.login.R

sealed class LoginError {
    abstract fun getErrorMessage(): Int

    data object NoEntry : LoginError() {
        override fun getErrorMessage(): Int = R.string.no_entry
    }

    data object NoError : LoginError() {
        override fun getErrorMessage(): Int = R.string.no_error
    }

    data object IncorrectUserName : LoginError() {
        override fun getErrorMessage(): Int = R.string.incorrect_user_name
    }

    data object IncorrectPassword : LoginError() {
        override fun getErrorMessage(): Int = R.string.incorrect_password
    }

    data object InvalidNameLength : LoginError() {
        override fun getErrorMessage(): Int = R.string.invalid_name_length
    }

    data object InvalidPasswordLength : LoginError() {
        override fun getErrorMessage(): Int = R.string.invalid_password_length
    }
}
