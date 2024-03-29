package com.wagnermessias.bankapp.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Statements(
    @SerializedName("statementList")
    val list: List<Statement>
): Serializable