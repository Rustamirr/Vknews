package com.example.vknews.domain.news

import com.example.vknews.domain.NewsRepository
import com.example.vknews.domain.core.BaseModel
import com.example.vknews.domain.core.Schedulers
import java.time.LocalDate
import javax.inject.Inject

class NewsModel
@Inject constructor(
    private val repository: NewsRepository,
    private val schedulers: Schedulers
) : BaseModel<NewsState>(NewsState()), NewsInteractor {

    override fun loadNews() = observeState()
        .firstOrError()
        .flatMap {
            repository.loadNews(it.pageKey, it.startDate, it.endDate)
                .subscribeOn(schedulers.io())
        }
        .doOnSuccess {
            updateState { copy(pageKey = it.pageKey) }
        }

    override fun setStartDate(startDate: LocalDate) {
        updateState { copy(startDate = startDate) }
    }

    override fun setEndDate(endDate: LocalDate) {
        updateState { copy(endDate = endDate) }
    }
}