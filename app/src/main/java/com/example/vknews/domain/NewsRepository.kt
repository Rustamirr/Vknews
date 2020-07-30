package com.example.vknews.domain

import io.reactivex.Single

interface NewsRepository {

    fun loadNews(): Single<List<NewsInfo>>
}