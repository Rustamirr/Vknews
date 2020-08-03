package com.example.vknews.data.news.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsNetworkApi {

    @GET("method/newsfeed.get?v=5.120&max_photos=1&count=10&filters=post")
    fun getNews(
        @Query("access_token") token: String,
        @Query("source_ids") sourceIds: String,
        @Query("start_from") pageKey: String?,
        @Query("start_time") startDate: Long,
        @Query("end_time") endDate: Long
    ): Single<GetNewsResponse>

    @GET("method/groups.search?v=5.120&type=group&count=1")
    fun getGroup(
        @Query("access_token") token: String,
        @Query("q") searchText: String
    ): Single<GetGroupsResponse>
}