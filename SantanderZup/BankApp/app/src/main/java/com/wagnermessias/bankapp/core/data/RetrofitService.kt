package com.wagnermessias.bankapp.core.data

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitService {
    companion object {
        private const val DATE_PATTERN = "yyyy-MM-dd"
        private const val BASE_URL = "https://bank-app-test.herokuapp.com"

        fun retrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(datePattern))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build()

        }

        private val client : OkHttpClient = OkHttpClient.Builder().apply {
            if (true) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                this.addInterceptor(interceptor)
            }
        }.build()

        private val datePattern = GsonBuilder()
            .setDateFormat(DATE_PATTERN)
            .create()
    }
}

