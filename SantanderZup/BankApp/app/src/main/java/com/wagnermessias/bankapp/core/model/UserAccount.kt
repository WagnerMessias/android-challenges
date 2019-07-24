package com.wagnermessias.bankapp.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserAccount(
    @SerializedName("userAccount")
    val account: Account
): Serializable