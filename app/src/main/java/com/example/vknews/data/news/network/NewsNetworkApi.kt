package com.example.vknews.data.news.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

/*
 https://api.vk.com/method/newsfeed.get?access_token=49e849cdba090e7d445f9c81a1220741cee9a6448fb2362e60fde13eb1b8ceb605b08598b077db4e2df52&v=5.120&max_photos=1&start_from=""&start_time=1593561600&end_time=1596067200&g<10328056>
 */
// SHA1 = 8D6CC8C3098A7562623EA55E3D1DC1374DE2FCB2
// Token = 49e849cdba090e7d445f9c81a1220741cee9a6448fb2362e60fde13eb1b8ceb605b08598b077db4e2df52
interface NewsNetworkApi {

    @GET("method/newsfeed.get?v=5.120&max_photos=1&count=10")
    fun getNews(
        @Query("access_token") token: String,
        @Query("source_ids") sourceIds: String,
        @Query("start_from") pageKey: String,
        @Query("start_time") startDate: LocalDate,
        @Query("end_time") endDate: LocalDate
    ): Single<Unit>
}