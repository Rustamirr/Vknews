package com.example.vknews.domain.news

import com.example.vknews.domain.core.Interactor
import io.reactivex.Single
import java.time.LocalDate

interface NewsInteractor : Interactor<NewsState> {

    fun loadNews(): Single<NewsFeed>

    fun setStartDate(startDate: LocalDate)

    fun setEndDate(endDate: LocalDate)
}