package com.example.vknews.data.news.network

import io.reactivex.Single
import java.time.LocalDate

interface NewsNetworkSource {

    fun getNews(
        token: String,
        sourceId: Long,
        pageKey: String?,
        startDate: LocalDate,
        endDate: LocalDate
    ): Single<GetNewsResponse>

    fun getGroupByText(token: String, searchText: String): Single<GetGroupsResponse>
}