package com.wagnermessias.bankapp.core

import android.app.Application
import com.wagnermessias.bankapp.core.di.StatementsModule
import com.wagnermessias.bankapp.core.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BankAppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@BankAppApplication)
            modules(loginModule,StatementsModule)
        }
    }
}