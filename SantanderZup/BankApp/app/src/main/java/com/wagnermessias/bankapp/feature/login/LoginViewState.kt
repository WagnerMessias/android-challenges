package com.wagnermessias.bankapp.feature.login

import com.wagnermessias.bankapp.core.model.UserAccount

sealed class LoginViewState{
    data class UserLogin(val userAccount: UserAccount?, val isLogged: Boolean): LoginViewState()
    data class LastUser(val value: String): LoginViewState()
 }
