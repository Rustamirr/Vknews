package com.example.vknews.data.news.network

import java.time.LocalDate
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
        1593561600,
        1596067200
        //startDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        //endDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    )

    override fun getGroupByText(token: String, searchText: String) =
        api.getGroup(token, searchText)
}