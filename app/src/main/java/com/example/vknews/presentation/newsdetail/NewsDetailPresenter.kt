package com.example.vknews.presentation.newsdetail

import com.example.vknews.domain.core.EmptyState
import com.example.vknews.domain.core.Logger
import com.example.vknews.domain.core.Schedulers
import com.example.vknews.domain.newsdetail.NewsDetailInteractor
import com.example.vknews.presentation.core.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class NewsDetailPresenter
@Inject constructor(
    interactor: NewsDetailInteractor,
    schedulers: Schedulers,
    logger: Logger
) : BasePresenter<EmptyState, NewsDetailView, NewsDetailInteractor>(
    interactor,
    schedulers,
    logger
)