package com.wagnermessias.bankapp.core.data.remote

import com.wagnermessias.bankapp.core.model.Statements
import com.wagnermessias.bankapp.core.model.User
import com.wagnermessias.bankapp.core.model.UserAccount
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BankApi {
    companion object {
        private const val LOGIN = "/api/login"
        private const val PARAM_USER_ID = "userId"
        private const val STATEMENTS = "/api/statements/{$PARAM_USER_ID}"
    }

    @POST(LOGIN)
    fun login(@Body user: User):Deferred<Response<UserAccount>>

    @GET(STATEMENTS)
    fun getStatements(@Path(PARAM_USER_ID) userId: Int): Deferred<Response<Statements>>
}