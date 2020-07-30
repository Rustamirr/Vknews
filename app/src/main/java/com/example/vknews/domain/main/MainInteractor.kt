package com.example.vknews.domain.main

import com.example.vknews.data.authorization.Token
import com.example.vknews.domain.core.EmptyState
import com.example.vknews.domain.core.Interactor
import io.reactivex.Completable

interface MainInteractor : Interactor<EmptyState> {

    fun loginPassed(token: Token): Completable
}