package com.example.vknews.data.news.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/*
 https://api.vk.com/method/newsfeed.get?access_token=9b515dc0ed749899f327ddecc743b4f3028931fe681d6db973d3f9d8024ade59c9c4f3cc627ab10b0be75&v=5.120&max_photos=1&count=10&start_from=""&start_time=1593561600&end_time=1596067200&source_ids=g10328056&filters=post
 https://api.vk.com/method/groups.search?access_token=9b515dc0ed749899f327ddecc743b4f3028931fe681d6db973d3f9d8024ade59c9c4f3cc627ab10b0be75&v=5.120&type=group&count=1&q=Ижевск новости
 */
// SHA1 = 8D6CC8C3098A7562623EA55E3D1DC1374DE2FCB2
// Token = 9b515dc0ed749899f327ddecc743b4f3028931fe681d6db973d3f9d8024ade59c9c4f3cc627ab10b0be75
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