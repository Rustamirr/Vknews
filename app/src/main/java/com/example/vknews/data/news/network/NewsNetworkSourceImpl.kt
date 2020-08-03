package com.example.vknews.data.news.network

import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import javax.inject.Inject

private const val SOURCE_FORMAT = "g%s"

class NewsNetworkSourceImpl
@Inject constructor(private val api: NewsNetworkApi) : NewsNetworkSource {

    override fun getNews(
        token: String,
        sourceId: Long,
        pageKey: String?,
        startDate: LocalDate,
        endDate: LocalDate
    ) = api.getNews(
        token,
        SOURCE_FORMAT.format(sourceId),
        pageKey,
        startDate.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond,
        endDate.atTime(23, 59, 59).toInstant(ZoneOffset.UTC).epochSecond
    )

    override fun getGroupByText(token: String, searchText: String) =
        api.getGroup(token, searchText)
}