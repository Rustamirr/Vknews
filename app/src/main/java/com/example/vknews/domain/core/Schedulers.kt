package com.example.vknews.domain.core

import io.reactivex.Scheduler

interface Schedulers {

    fun io(): Scheduler

    fun computation(): Scheduler

    fun main(): Scheduler
}