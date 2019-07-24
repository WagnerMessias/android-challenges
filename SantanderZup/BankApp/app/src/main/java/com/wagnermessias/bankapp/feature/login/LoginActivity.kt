package com.wagnermessias.bankapp.feature.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.wagnermessias.bankapp.R
import com.wagnermessias.bankapp.core.extension.bindView
import com.wagnermessias.bankapp.core.model.Account
import com.wagnermessias.bankapp.core.model.UserAccount
import com.wagnermessias.bankapp.feature.statements.StatementsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val inputUser: EditText by this.bindView(R.id.login_user_input)
    private val inputPassword: EditText by bindView(R.id.login_password_input)
    private val buttonSingin: Button by bindView(R.id.login_signin)
    private val message: TextView by bindView(R.id.login_message)
    private val progress: ProgressBar by bindView(R.id.login_progress)

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        initListeners()
        observeViewModel()
        loginViewModel.init()
    }

    private fun initListeners() {
        buttonSingin.setOnClickListener{
            progress.visibility = View.VISIBLE
            loginViewModel.userAuthenticate(inputUser.text.toString(), inputPassword.text.toString())
        }
    }

    private fun observeViewModel(){
        loginViewModel.viewState.observe(this, Observer {
            when(it){
                is LoginViewState.UserLogin -> checkLogged(it.userAccount,it.isLogged)
                is LoginViewState.LastUser -> showLastUser(it.value)
            }
        })
    }

    private fun checkLogged(uAccount: UserAccount?, logged: Boolean) {
        if(logged){
            uAccount?.account.let {
                    openStatements(it)
            }
        }else{
            showLoginMessage(logged)
        }
    }

    private fun showLastUser(value: String) = inputUser.setText(value)

    private fun openStatements(account: Account?) {
        account?.let {
            hideLoading()
            startActivity(StatementsActivity.newInstance(this, it))
            finish()
        }

    }

    private fun showLoginMessage(loginIsSuccess: Boolean){
        if(loginIsSuccess){
            message.setTextColor(resources.getColor(R.color.default_success))
            message.text = getString(R.string.login_success)
        }else{
            hideLoading()
            message.setTextColor(resources.getColor(R.color.default_error))
            message.text = getString(R.string.login_user_or_pasword_invalid)
        }
    }

    private fun hideLoading(){
        progress.visibility = View.INVISIBLE
    }

    companion object {
        fun init(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
