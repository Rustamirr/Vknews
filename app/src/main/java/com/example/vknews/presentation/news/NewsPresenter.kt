package com.example.vknews.presentation.news

import android.os.Handler
import android.os.Looper
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.vknews.domain.core.Logger
import com.example.vknews.domain.core.Schedulers
import com.example.vknews.domain.news.NewsInfo
import com.example.vknews.domain.news.NewsInteractor
import com.example.vknews.domain.news.NewsState
import com.example.vknews.presentation.core.BasePresenter
import com.example.vknews.presentation.datepicker.DatePickerDate
import com.example.vknews.presentation.datepicker.DatePickerDateType.END_DATE
import com.example.vknews.presentation.datepicker.DatePickerDateType.START_DATE
import com.example.vknews.presentation.navigation.Screen
import com.example.vknews.presentation.news.adapter.NewsItem
import io.reactivex.rxkotlin.subscribeBy
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import java.time.LocalDate
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

private const val PAGE_SIZE = 10

@InjectViewState
class NewsPresenter
@Inject constructor(
    private val router: Router,
    interactor: NewsInteractor,
    schedulers: Schedulers,
    logger: Logger
) : BasePresenter<NewsState, NewsView, NewsInteractor>(
    interactor,
    schedulers,
    logger
) {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        initLoading()
        mapStateToRender(
            NewsState::startDate,
            viewState::renderStartDate
        )
        mapStateToRender(
            NewsState::endDate,
            viewState::renderEndDate
        )
    }

    fun onStartDateClick(startDate: LocalDate) {
        router.navigateTo(
            Screen.Dialog.DatePicker(DatePickerDate(startDate, START_DATE))
        )
    }

    fun onEndDateClick(endDate: LocalDate) {
        router.navigateTo(
            Screen.Dialog.DatePicker(DatePickerDate(endDate, END_DATE))
        )
    }

    fun onDateChosen(datePickerDate: DatePickerDate) {
        when (datePickerDate.dateType) {
            START_DATE -> interactor.setStartDate(datePickerDate.date)
            END_DATE -> interactor.setEndDate(datePickerDate.date)
        }
        initLoading()
    }

    fun onNewsItemClick(newsItem: NewsItem) {
        router.navigateTo(Screen.NewsDetail(newsItem))
    }

    private fun initLoading() {
        val pagedListConfig = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        val pagedList = PagedList.Builder(PagedDataSource(), pagedListConfig)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(MainThreadExecutor())
            .build()
        viewState.renderList(pagedList)
    }

    private fun loadNews(callbackAction: (list: List<NewsItem>, pageKey: String?) -> Unit) {
        disposeOnDestroy(
            interactor.loadNews()
                .subscribeBy(
                    onSuccess = {
                        callbackAction(it.newsInfo.map(NewsInfo::toNewsItem), it.pageKey)
                    },
                    onError = {
                        logger.logError(it)
                        viewState.showErrorOccurred()
                    }
                )
        )
    }

    inner class PagedDataSource : PageKeyedDataSource<String, NewsItem>() {
        override fun loadInitial(
            params: LoadInitialParams<String>,
            callback: LoadInitialCallback<String, NewsItem>
        ) {
            loadNews { list, nextPageKey -> callback.onResult(list, null, nextPageKey) }
        }

        override fun loadAfter(
            params: LoadParams<String>,
            callback: LoadCallback<String, NewsItem>
        ) {
            loadNews { list, nextPageKey -> callback.onResult(list, nextPageKey) }
        }

        override fun loadBefore(
            params: LoadParams<String>,
            callback: LoadCallback<String, NewsItem>
        ) {
            // no op
        }
    }
}

private class MainThreadExecutor : Executor {
    private val mHandler: Handler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        mHandler.post(command)
    }
}