package com.wagnermessias.bankapp.feature.statements

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wagnermessias.bankapp.R
import com.wagnermessias.bankapp.core.extension.bindView
import com.wagnermessias.bankapp.core.extension.formatAgency
import com.wagnermessias.bankapp.core.extension.toCurrency
import com.wagnermessias.bankapp.core.model.Account
import com.wagnermessias.bankapp.core.model.Statements
import com.wagnermessias.bankapp.feature.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatementsActivity : AppCompatActivity() {
    private val accountName: TextView by this.bindView(R.id.statements_account_name)
    private val accountNumber: TextView by this.bindView(R.id.statements_account)
    private val accountBalance: TextView by this.bindView(R.id.statements_balance)
    private val accountLogout: ImageView by this.bindView(R.id.statements_logout)
    private val listStatement: RecyclerView by this.bindView(R.id.statements_rv)

    private val statementViewModel: StatementsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statements_activity)

        intent.extras?.let { loadAccountAndSatatments(it) }
        observeViewModel()
        initListeners()
    }

    private fun observeViewModel() {
        statementViewModel.viewState.observe(this, Observer {
            when (it) {
                is StatementsViewState.User -> setupViews(it.account)
                is StatementsViewState.StatementsList -> setStatements(it.items)
            }
        })
    }

    private fun initListeners() {
        accountLogout.setOnClickListener { logout() }
    }

    private fun setupViews(account: Account) {
        val formattedAccount = "${account.bankAccount} / ${account.agency.formatAgency()}"
        accountName.text = account.name
        accountNumber.text = formattedAccount
        accountBalance.text = account.balance.toCurrency()
        listStatement.layoutManager = LinearLayoutManager(this)
    }

    private fun setStatements(statements: Statements) {
        listStatement.adapter = StatementsAdapter(statements.list)
    }

    private fun loadAccountAndSatatments(bundle: Bundle) {
        val account = bundle.getSerializable(ACCOUNT_KEY) as Account
        account.let {
            statementViewModel.setAccount(account)
            statementViewModel.loadStatments(account.userId.toInt())
        }
    }

    private fun logout() {
        startActivity(LoginActivity.init(this))
        finish()
    }

    companion object {
        private const val ACCOUNT_KEY = "account_param"

        fun newInstance(
            context: Context,
            account: Account
        ) = Intent(context, StatementsActivity::class.java).apply {
            Bundle().apply {
                putSerializable(ACCOUNT_KEY, account)
                putExtras(this)
            }
        }
    }
}
