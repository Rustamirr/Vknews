package com.example.vknews.presentation.datepicker

import com.example.vknews.domain.core.EmptyState
import com.example.vknews.domain.core.Logger
import com.example.vknews.domain.core.Schedulers
import com.example.vknews.domain.datepicker.DatePickerInteractor
import com.example.vknews.presentation.core.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class DatePickerPresenter
@Inject constructor(
    interactor: DatePickerInteractor,
    schedulers: Schedulers,
    logger: Logger
) : BasePresenter<EmptyState, DatePickerView, DatePickerInteractor>(
    interactor,
    schedulers,
    logger
)