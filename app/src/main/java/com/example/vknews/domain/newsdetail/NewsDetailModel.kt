package com.example.vknews.domain.newsdetail

import com.example.vknews.domain.core.BaseModel
import com.example.vknews.domain.core.EmptyState
import javax.inject.Inject

class NewsDetailModel
@Inject constructor() : BaseModel<EmptyState>(EmptyState), NewsDetailInteractor