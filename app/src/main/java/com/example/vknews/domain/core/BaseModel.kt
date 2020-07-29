package com.example.vknews.domain.core

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

abstract class BaseModel<S>(defaultState: S) : Interactor<S> {

    private var startState: S = defaultState

    private val stateSubject: Subject<S> by lazy {
        BehaviorSubject.createDefault(startState).toSerialized()
    }

    protected fun getState(): S = stateSubject.blockingFirst()

    protected fun updateState(state: S) {
        stateSubject.onNext(state)
    }

    protected inline fun updateState(updater: S.() -> S) {
        updateState(updater(getState()))
    }

    override fun observeState(): Observable<S> = stateSubject.hide()
}