package com.dai.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by dai on 2017/8/30.
 */
class TokenStorage(context: Context) {

    private val keyToken = "token"

    val token: SharedPreferences by lazy {
        context.getSharedPreferences(keyToken, Context.MODE_PRIVATE)
    }

    fun setToken(value: String) {
        token.edit().putString(keyToken, value).apply()
    }

    fun getToken(): String = token.getString(keyToken, null)
}