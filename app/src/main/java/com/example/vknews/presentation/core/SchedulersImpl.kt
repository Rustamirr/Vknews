package com.example.vknews.presentation.core

import com.example.vknews.domain.core.Schedulers
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SchedulersImpl
@Inject constructor() : Schedulers {

    override fun io(): Scheduler = io.reactivex.schedulers.Schedulers.io()

    override fun computation(): Scheduler = io.reactivex.schedulers.Schedulers.computation()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}