package com.wagnermessias.bankapp.core.data.local

import android.content.Context
import android.content.SharedPreferences

class BankPreferences(context: Context) {

    val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveString(KEY_NAME: String, value: String) {
        val editor: SharedPreferences.Editor = preferences.edit()

        editor.putString(KEY_NAME, value)

        editor.commit()
    }

    fun getValueString(KEY_NAME: String): String? {

        return preferences.getString(KEY_NAME, null)
    }

    companion object {
        private const val PREFS_NAME = "bank_preferences"
    }
}

