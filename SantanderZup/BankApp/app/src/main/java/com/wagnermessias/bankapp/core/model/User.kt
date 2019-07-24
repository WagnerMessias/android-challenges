package com.wagnermessias.bankapp.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User (
    @SerializedName("user")
    val user: String,

    @SerializedName("password")
    var password: String
    ): Serializable
