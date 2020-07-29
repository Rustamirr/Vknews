package com.example.vknews.domain.datepicker

import com.example.vknews.domain.core.BaseModel
import com.example.vknews.domain.core.EmptyState
import javax.inject.Inject

class DatePickerModel
@Inject constructor() : BaseModel<EmptyState>(EmptyState), DatePickerInteractor