package com.wagnermessias.bankapp.feature.statements

import com.wagnermessias.bankapp.core.model.Account
import com.wagnermessias.bankapp.core.model.Statements
import com.wagnermessias.bankapp.core.model.UserAccount

sealed class StatementsViewState{
    data class User(val account: Account): StatementsViewState()
    data class StatementsList(val items: Statements): StatementsViewState()
    data class LoadStamentsErro(val value: Boolean ): StatementsViewState()
 }
