package com.example.login.presentation.validator

import com.example.login.presentation.error.LoginError

private const val USERNAME_LENGTH = 5
private const val PASSWORD_MIN_LENGTH = 7

object LoginValidator {

    fun userNameError(username: String): LoginError {
        return when {
            username.isEmpty() -> LoginError.NoEntry
            !isValidUserNameLength(username) -> LoginError.InvalidNameLength
            !username.isAlphaNumeric() -> LoginError.IncorrectUserName
            else -> LoginError.NoError
        }
    }

    fun passwordError(password: String): LoginError {
        return when {
            password.isEmpty() -> LoginError.NoEntry
            !isValidPasswordLength(password) -> LoginError.InvalidPasswordLength
            !password.isAlphaNumericWithSpecialCharacters() -> LoginError.IncorrectPassword
            else -> LoginError.NoError
        }
    }

    fun checkforLoginButton(passwordError: LoginError, userNameError: LoginError): Boolean {
        return passwordError is LoginError.NoError && userNameError is LoginError.NoError
    }

    private fun String.isAlphaNumericWithSpecialCharacters(): Boolean {
        val containsLowerCase = any { it.isLowerCase() }
        val containsUpperCase = any { it.isUpperCase() }
        val containsSpecialCharacters = any { !it.isLetterOrDigit() }
        val containsDigits = any { it.isDigit() }
        return containsDigits && containsLowerCase && containsUpperCase && containsSpecialCharacters
    }

    private fun isValidPasswordLength(password: String): Boolean =
        password.count() < PASSWORD_MIN_LENGTH

    private fun isValidUserNameLength(userName: String): Boolean =
        userName.count() > USERNAME_LENGTH

    private fun String.isAlphaNumeric() = matches("[a-zA-Z0-9]+".toRegex())
}
