package com.example.vknews.domain.main

import com.example.vknews.data.authorization.Token
import com.example.vknews.domain.core.EmptyState
import com.example.vknews.domain.core.Interactor
import io.reactivex.Completable
import io.reactivex.Single

interface MainInteractor : Interactor<EmptyState> {

    fun isAuth(): Single<Boolean>

    fun authorizationPassed(token: Token): Completable
}