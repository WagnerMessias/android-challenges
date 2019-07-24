package com.wagnermessias.bankapp.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Statement(
    @SerializedName("title")
    val title: String,

    @SerializedName("desc")
    val description: String,

    @SerializedName("date")
    val date: Date,

    @SerializedName("value")
    val value: Float
): Serializable