package com.example.vknews.data.news.network

import io.reactivex.Single
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import javax.inject.Inject

private const val GROUP_FORMAT = "g%s"

class NewsNetworkSourceImpl
@Inject constructor(private val api: NewsNetworkApi) : NewsNetworkSource {

    override fun getNews(
        token: String,
        sourceId: Long,
        pageKey: String?,
        startDate: LocalDate,
        endDate: LocalDate
    ): Single<GetNewsResponse> {
        val timeZone = ZoneOffset.UTC
        return api.getNews(
            token,
            GROUP_FORMAT.format(sourceId),
            pageKey,
            startDate.atStartOfDay().toInstant(timeZone).epochSecond,
            endDate.atTime(LocalTime.MAX).toInstant(timeZone).epochSecond
        )
    }

    override fun getGroupByText(token: String, searchText: String) = api.getGroup(token, searchText)
}