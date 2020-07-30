package com.example.vknews.data.authorization

interface TokenSource {

    fun setToken(token: Token)

    fun getToken(): Token

    fun clearToken()

    fun isAuth(): Boolean
}