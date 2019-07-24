package com.wagnermessias.bankapp.core.interactor

import com.wagnermessias.bankapp.core.data.StatementsRepository
import com.wagnermessias.bankapp.core.model.Statements

class StatementsInteractor(
    private val repository: StatementsRepository
) {
    suspend fun getStatements(userId: Int): Statements? {
        val call = repository.execute(userId)
        val response = call.await()

        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
