package com.example.vknews.domain.datepicker

import dagger.Binds
import dagger.Module

@Module
interface DatePickerModule {

    @Binds
    fun bindDatePickerInteractor(datePickerModel: DatePickerModel): DatePickerInteractor
}