package com.example.vknews.data.authorization

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

private const val TOKEN = "TOKEN"

class TokenSourceImpl
@Inject constructor(private val sharedPreferences: SharedPreferences) : TokenSource {

    override fun setToken(token: Token) {
        sharedPreferences.edit(commit = true) {
            putString(TOKEN, token.value)
        }
    }

    override fun getToken() = Token(sharedPreferences.getString(TOKEN, null))

    override fun clearToken() {
        sharedPreferences.edit(commit = true) {
            remove(TOKEN)
        }
    }

    override fun isAuth() = getToken().value != null
}