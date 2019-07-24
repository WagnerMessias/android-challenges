package com.wagnermessias.bankapp.util.rule

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.wagnermessias.bankapp.core.interactor.StatementsInteractor
import com.wagnermessias.bankapp.core.model.Statements
import com.wagnermessias.bankapp.feature.statements.StatementsViewModel
import com.wagnermessias.bankapp.feature.statements.StatementsViewState
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class StatementsViewModelTest {

    @get:Rule
    val rule = instantLiveDataAndCoroutineRules

    private val mockStatementsInteractor: StatementsInteractor = mock()
    private val viewModel = StatementsViewModel(mockStatementsInteractor)

    private val mockStatements: Statements = mock()
    private val mockException: NullPointerException = mock()

    @Test
    fun SuccessExpectd_loadStatements_stateReceivesStatementsLis() {
        val expectedLoadStatements = StatementsViewState.StatementsList(mockStatements)

        val userId = 1

        whenever(runBlocking {
            mockStatementsInteractor.getStatements(userId)
        }) doReturn mockStatements

        viewModel.loadStatments(userId)

        Assert.assertEquals(
            expectedLoadStatements,
            viewModel.viewState.value
        )
    }

    @Test
    fun requestFailed_loadStatements_statestatementsListTrue() {
        val expectedLoadStatementsErro = StatementsViewState.LoadStamentsErro(true)

        val userId = 1

        whenever(runBlocking {
            mockStatementsInteractor.getStatements(userId)
        }) doThrow mockException

        viewModel.loadStatments(userId)

        Assert.assertEquals(
            expectedLoadStatementsErro,
            viewModel.viewState.value
        )
    }

}
