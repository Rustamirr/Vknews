package com.example.vknews.presentation.news

import androidx.paging.PagedList
import com.example.vknews.presentation.core.BaseView
import com.example.vknews.presentation.news.adapter.NewsItem
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.time.LocalDate

interface NewsView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderStartDate(startDate: LocalDate)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderEndDate(endDate: LocalDate)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderList(list: PagedList<NewsItem>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showErrorOccurred()
}