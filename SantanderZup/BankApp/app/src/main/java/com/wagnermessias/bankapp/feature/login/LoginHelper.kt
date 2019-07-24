package com.wagnermessias.bankapp.feature.login

import com.wagnermessias.bankapp.core.extension.isCpf
import com.wagnermessias.bankapp.core.extension.isEmail

class LoginHelper {

    fun isValidLogin(user: String, password: String): Boolean {
        return isValidUser(user) && isValidPassword(password)
    }

    fun isValidUser(user: String) = (user.isCpf() || user.isEmail())

    fun isValidPassword(password: String): Boolean {
        return password.contains(UPPER_PATTERN.toRegex()) &&
        password.contains(SPECIAL_PATTERN.toRegex()) &&
        password.contains(ALPHANUMERIC_PATTERN.toRegex())
    }

    companion object {
        const val SPECIAL_PATTERN = "[^A-Za-z0-9_]"
        const val UPPER_PATTERN = "[A-Z]${"+"}"
        const val ALPHANUMERIC_PATTERN = "[a-zA-Z0-9]${"+"}"
    }
}