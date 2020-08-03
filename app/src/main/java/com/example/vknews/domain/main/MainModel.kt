package com.example.vknews.domain.main

import com.example.vknews.data.authorization.Token
import com.example.vknews.domain.NewsRepository
import com.example.vknews.domain.core.BaseModel
import com.example.vknews.domain.core.EmptyState
import javax.inject.Inject

class MainModel
@Inject constructor(
    private val repository: NewsRepository
) : BaseModel<EmptyState>(EmptyState), MainInteractor {

    override fun isAuth() = repository.isAuth()

    override fun authorizationPassed(token: Token) = repository.saveToken(token)
}