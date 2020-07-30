package com.example.vknews.presentation.news

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.paging.PageKeyedDataSource
import com.example.vknews.domain.core.Logger
import com.example.vknews.domain.core.Schedulers
import com.example.vknews.domain.news.NewsInteractor
import com.example.vknews.domain.news.NewsState
import com.example.vknews.presentation.core.BasePresenter
import com.example.vknews.presentation.navigation.Screen
import com.example.vknews.presentation.news.adapter.NewsItem
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import java.time.LocalDate
import java.util.concurrent.Executor
import javax.inject.Inject

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
        router.navigateTo(Screen.Dialog.DatePicker(startDate, DateType.START_DATE))
    }

    fun onEndDateClick(endDate: LocalDate) {
        router.navigateTo(Screen.Dialog.DatePicker(endDate, DateType.END_DATE))
    }

    fun onDateChosen(bundle: Bundle) {
        val dateType = bundle.get(DATE_PICKER_DATE_TYPE) as DateType
        val date = bundle.get(DATE_PICKER_DATE) as LocalDate
        when (dateType) {
            DateType.START_DATE -> interactor.setStartDate(date)
            DateType.END_DATE -> interactor.setEndDate(date)
        }
    }

    fun onNewsItemClick(newsItem: NewsItem) {
        //router.navigateTo(Screen.NewsDetail(newsItem.id))
    }

    fun onFindButtonClick() {
        /*val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .build()
        val pagedList = PagedList.Builder(PagedDataSource(), pagedListConfig)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(MainThreadExecutor())
            .build()
        viewState.renderList(pagedList)*/
    }

    private fun loadPagePhotosInfo(
        page: Int,
        callbackAction: (list: List<NewsItem>) -> Unit
    ) {
        /*disposeOnDestroy(
            interactor.loadNews()
                .observeOn(schedulers.main())
                .subscribeBy(
                    onSuccess = {
                        callbackAction(it.map(NewsInfo::toNewsItem))
                    },
                    onError = {
                        logger.logError(it)
                        viewState.showErrorOccurred()
                    }
                )
        )*/
    }

    inner class PagedDataSource : PageKeyedDataSource<Int, NewsItem>() {
        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, NewsItem>
        ) {
            /*loadPagePhotosInfo(START_PAGE) { list: List<PhotoInfoItem> ->
                callback.onResult(list, null, START_PAGE)
            }*/
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, NewsItem>
        ) {
            val page = params.key + 1
            loadPagePhotosInfo(page) { list: List<NewsItem> ->
                callback.onResult(list, page)
            }
        }

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, NewsItem>
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