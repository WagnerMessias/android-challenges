package com.wagnermessias.bankapp.core.di

import com.wagnermessias.bankapp.core.data.RetrofitService
import com.wagnermessias.bankapp.core.data.StatementsRepository
import com.wagnermessias.bankapp.core.data.UserRepository
import com.wagnermessias.bankapp.core.data.local.BankPreferences
import com.wagnermessias.bankapp.core.data.remote.BankApi
import com.wagnermessias.bankapp.core.interactor.StatementsInteractor
import com.wagnermessias.bankapp.core.interactor.UserInteractor
import com.wagnermessias.bankapp.feature.login.LoginHelper
import com.wagnermessias.bankapp.feature.login.LoginViewModel
import com.wagnermessias.bankapp.feature.statements.StatementsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    single { BankPreferences(get()) }
    single { RetrofitService.retrofit().create<BankApi>(BankApi::class.java) }
    factory { UserRepository(get(), get()) }
    factory { UserInteractor(get()) }
    factory { LoginHelper() }
    viewModel { LoginViewModel(get(), get()) }
}

val StatementsModule = module {
    factory { StatementsRepository(get()) }
    factory { StatementsInteractor(get()) }
    viewModel { StatementsViewModel(get()) }
}