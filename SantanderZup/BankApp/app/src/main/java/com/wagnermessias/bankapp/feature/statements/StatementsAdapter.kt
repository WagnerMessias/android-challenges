package com.wagnermessias.bankapp.feature.statements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wagnermessias.bankapp.R
import com.wagnermessias.bankapp.core.extension.bindView
import com.wagnermessias.bankapp.core.extension.format
import com.wagnermessias.bankapp.core.extension.toCurrency
import com.wagnermessias.bankapp.core.model.Statement


internal class StatementsAdapter(private val statements: List<Statement>)
    : RecyclerView.Adapter<StatementsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.statemens_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = statements.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            title.text = statements[position].title
            description.text = statements[position].description
            date.text = statements[position].date.format()
            value.text = statements[position].value.toCurrency()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView by bindView(R.id.statement_item_title)
        val description: TextView by bindView(R.id.statement_item_desc)
        val date: TextView by bindView(R.id.statement_item_date)
        val value: TextView by bindView(R.id.statement_item_value)
    }
}