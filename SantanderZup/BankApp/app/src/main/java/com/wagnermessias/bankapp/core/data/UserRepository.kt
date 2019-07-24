package com.wagnermessias.bankapp.core.data

import com.wagnermessias.bankapp.core.data.local.BankPreferences
import com.wagnermessias.bankapp.core.data.remote.BankApi
import com.wagnermessias.bankapp.core.model.User
import com.wagnermessias.bankapp.core.model.UserAccount
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository(
    private val bankApi: BankApi,
    private val preferences: BankPreferences
) {
    suspend fun execute(
        user: User
    ): Deferred<Response<UserAccount>> = withContext(Dispatchers.IO) {
        bankApi.login(user)
    }

    fun saveLastUser(lastUser: String) {
        preferences.saveString("last_user", lastUser)
    }

    fun getLastUser(): String? {
        return preferences.getValueString("last_user")
    }
}