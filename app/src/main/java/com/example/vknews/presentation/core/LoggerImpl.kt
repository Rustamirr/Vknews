package com.example.vknews.presentation.core

import com.example.vknews.domain.core.Logger
import timber.log.Timber
import javax.inject.Inject

class LoggerImpl
@Inject constructor() : Logger {

    override fun logError(throwable: Throwable) {
        Timber.e(throwable)
    }
}