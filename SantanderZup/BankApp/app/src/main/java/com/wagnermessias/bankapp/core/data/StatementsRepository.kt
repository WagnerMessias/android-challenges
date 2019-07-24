package com.wagnermessias.bankapp.core.data

import com.wagnermessias.bankapp.core.data.remote.BankApi
import com.wagnermessias.bankapp.core.model.Statements
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class StatementsRepository(
    private val bankApi: BankApi
) {
    suspend fun execute(
        userId: Int
    ): Deferred<Response<Statements>> = withContext(Dispatchers.IO) {
        bankApi.getStatements(userId)
    }
}
