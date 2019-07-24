package com.wagnermessias.bankapp.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wagnermessias.bankapp.core.base.BaseViewModel
import com.wagnermessias.bankapp.core.interactor.UserInteractor
import com.wagnermessias.bankapp.core.model.User
import com.wagnermessias.bankapp.core.model.UserAccount
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userInteractor: UserInteractor,
    private val loginHelper: LoginHelper
) : BaseViewModel() {

    private val state = MutableLiveData<LoginViewState>()

    val viewState: LiveData<LoginViewState> = state

    fun init() {
        val userLast = userInteractor.getLastUser()
        userLast?.let { state.value = LoginViewState.LastUser(it) }
    }

    fun userAuthenticate(user: String, password: String) {
        if (loginHelper.isValidLogin(user, password)) {
            val userLogin = User(user, password)
            signIn(userLogin)
        } else {
            setLoginInvalid()
        }
    }

    private fun signIn(userLogin: User) {
        launch {
            try {
                val userAccount = userInteractor.signInUser(userLogin)

                if (userAccount != null) {
                    userInteractor.saveLastUser(userLogin.user)
                    notifyViewLoginSuccess(userAccount)
                } else {
                    setLoginInvalid()
                }

            } catch (e: Exception) {
                setLoginInvalid()
            }
        }
    }

    private fun notifyViewLoginSuccess(userAccout: UserAccount?) {
        state.value = userAccout?.let { LoginViewState.UserLogin(it, true) }
    }

    private fun setLoginInvalid() {
        state.value = LoginViewState.UserLogin(null, false)
    }

}