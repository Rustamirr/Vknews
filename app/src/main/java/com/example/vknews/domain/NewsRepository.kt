package com.example.vknews.domain

import com.example.vknews.data.authorization.Token
import com.example.vknews.domain.news.NewsFeed
import io.reactivex.Completable
import io.reactivex.Single
import java.time.LocalDate

interface NewsRepository {

    fun isAuth(): Single<Boolean>

    fun saveToken(token: Token): Completable

    fun loadNews(pageKey: String?, startDate: LocalDate, endDate: LocalDate): Single<NewsFeed>
}