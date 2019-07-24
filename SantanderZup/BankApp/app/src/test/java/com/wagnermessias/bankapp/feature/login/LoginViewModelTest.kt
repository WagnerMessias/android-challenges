package com.wagnermessias.bankapp.feature.login

import com.nhaarman.mockitokotlin2.*
import com.wagnermessias.bankapp.core.interactor.UserInteractor
import com.wagnermessias.bankapp.core.model.UserAccount
import com.wagnermessias.bankapp.util.rule.instantLiveDataAndCoroutineRules
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val rule = instantLiveDataAndCoroutineRules

    private val mockUserInteractor: UserInteractor = mock()
    private val mockLoginHelper: LoginHelper = mock()
    private val viewModel = LoginViewModel(mockUserInteractor, mockLoginHelper)

    private val mockUserAccount: UserAccount = mock()
    private val mockException: NullPointerException = mock()

    @Test
    fun userORPasswordFormatNotExpectd_Authenticate_stateUserLoginFalse() {
        val expectedLoginIsInvalid = LoginViewState.UserLogin(null,false)

        val user = "aa"
        val password = "@#$"

        whenever(mockLoginHelper.isValidLogin(user, password)) doReturn false

        viewModel.userAuthenticate(user, password)

        Assert.assertEquals(
            expectedLoginIsInvalid,
            viewModel.viewState.value
        )
    }

    @Test
    fun userValid_Authenticate_stateUserLoginTrue() {
        val expectedLoginIsInvalid = LoginViewState.UserLogin(mockUserAccount,true)

        val user = "messias@gmail.com"
        val password = "Te@1"

        whenever(mockLoginHelper.isValidLogin(user, password)) doReturn true

        whenever(runBlocking {
            mockUserInteractor.signInUser(any()) }) doReturn mockUserAccount

        viewModel.userAuthenticate(user,password)

        Assert.assertEquals(
            expectedLoginIsInvalid,
            viewModel.viewState.value
        )
    }

    @Test
    fun requestFailed_Authenticate_stateUserLoginFalse() {
        val expectedLoginIsInvalid = LoginViewState.UserLogin(null,false)

        val user = "messias@gmail.com"
        val password = "Te@1"

        whenever(mockLoginHelper.isValidLogin(user, password)) doReturn true

        whenever(runBlocking {
            mockUserInteractor.signInUser(any()) }) doThrow mockException

        viewModel.userAuthenticate(user,password)

        Assert.assertEquals(
            expectedLoginIsInvalid,
            viewModel.viewState.value
        )
    }

}
