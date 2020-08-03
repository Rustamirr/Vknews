package com.example.vknews.presentation.mainactivity

import com.example.vknews.presentation.core.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainActivityView : BaseView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onTokenRequired()
}