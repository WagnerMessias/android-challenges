package com.wagnermessias.bankapp.core.interactor

import com.wagnermessias.bankapp.core.data.UserRepository
import com.wagnermessias.bankapp.core.model.User
import com.wagnermessias.bankapp.core.model.UserAccount

class UserInteractor(
    private val repository: UserRepository
) {

    fun saveLastUser(lastUser: String) {
        repository.saveLastUser(lastUser)
    }

    fun getLastUser(): String? {
        return repository.getLastUser()
    }

    suspend fun signInUser(userLogin: User): UserAccount? {
        val call = repository.execute(userLogin)
        val response = call.await()

        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}