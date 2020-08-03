package com.example.vknews.data.news

import com.example.vknews.data.authorization.Token
import com.example.vknews.data.authorization.TokenSource
import com.example.vknews.data.news.network.NewsNetworkSource
import com.example.vknews.data.news.network.toNewsFeed
import com.example.vknews.domain.NewsRepository
import com.example.vknews.domain.news.NewsFeed
import io.reactivex.Completable
import io.reactivex.Single
import java.time.LocalDate
import javax.inject.Inject

private const val TOKEN_REQUIRED_EXCEPTION = "Token required exception"
private const val GROUP_IZHEVSK_NOT_FOUND_EXCEPTION = "Token required exception"
private const val GROUP_NAME = "Ижевск новости"

class NewsRepositoryImpl
@Inject constructor(
    private val tokenSource: TokenSource,
    private val networkSource: NewsNetworkSource
) : NewsRepository {

    override fun saveToken(token: Token) = Completable.fromAction { tokenSource.setToken(token) }

    override fun loadNews(
        pageKey: String?,
        startDate: LocalDate,
        endDate: LocalDate
    ): Single<NewsFeed> = getToken()
        .flatMap { token ->
            getGroupIzhevsk(token)
                .map { token to it }
        }
        .flatMap { pair ->
            val (token, groupResponse) = pair
            networkSource.getNews(token, groupResponse.id, pageKey, startDate, endDate)
                .map { it.toNewsFeed(groupResponse) }
        }

    private fun getToken() = Single.fromCallable { tokenSource.getToken() }
        .map {
            requireNotNull(it.value) { TOKEN_REQUIRED_EXCEPTION }
        }

    private fun getGroupIzhevsk(token: String) = networkSource.getGroupByText(token, GROUP_NAME)
        .map {
            requireNotNull(it.groupsResponse.groupResponses?.firstOrNull()) { GROUP_IZHEVSK_NOT_FOUND_EXCEPTION }
        }
}