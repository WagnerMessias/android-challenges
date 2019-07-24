package com.wagnermessias.bankapp.feature.statements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wagnermessias.bankapp.core.base.BaseViewModel
import com.wagnermessias.bankapp.core.interactor.StatementsInteractor
import com.wagnermessias.bankapp.core.model.Account
import kotlinx.coroutines.launch

class StatementsViewModel(
    private val statementsInteractor: StatementsInteractor
) : BaseViewModel() {

    private val state = MutableLiveData<StatementsViewState>()

    val viewState: LiveData<StatementsViewState> = state

    fun loadStatments(userId: Int) {
        launch {
            try {
                val statements = statementsInteractor.getStatements(userId)

                if (statements != null) {
                    state.value = statements?.let { StatementsViewState.StatementsList(it) }
                } else {
                    setErroLoadStatments()
                }
            } catch (e: Exception) {
                setErroLoadStatments()
            }
        }
    }

    private fun setErroLoadStatments() {
        state.value = StatementsViewState.LoadStamentsErro(true)
    }

    fun setAccount(account: Account) {
        state.value = StatementsViewState.User(account)
    }
}