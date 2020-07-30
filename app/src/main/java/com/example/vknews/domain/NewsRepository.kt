package com.example.vknews.domain

import com.example.vknews.data.authorization.Token
import io.reactivex.Completable
import io.reactivex.Single

interface NewsRepository {

    fun saveToken(token: Token): Completable

    fun loadNews(): Single<List<NewsInfo>>

}