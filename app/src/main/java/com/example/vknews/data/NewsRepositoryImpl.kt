package com.example.vknews.data

import com.example.vknews.data.authorization.Token
import com.example.vknews.data.authorization.TokenSource
import com.example.vknews.domain.NewsInfo
import com.example.vknews.domain.NewsRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class NewsRepositoryImpl
@Inject constructor(
    private val tokenSource: TokenSource
) : NewsRepository {

    override fun saveToken(token: Token) = Completable.fromAction {
        tokenSource.setToken(token)
    }

    override fun loadNews(): Single<List<NewsInfo>> {
        TODO("Not yet implemented")
    }
}