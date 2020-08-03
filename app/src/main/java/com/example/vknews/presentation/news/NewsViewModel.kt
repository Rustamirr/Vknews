package com.example.vknews.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.vknews.presentation.core.SingleLiveEvent
import com.example.vknews.presentation.datepicker.DatePickerDate

class NewsViewModel : ViewModel() {

    private val datePickerLiveData = SingleLiveEvent<DatePickerDate>()

    fun observeDatePicker(): LiveData<DatePickerDate> = datePickerLiveData

    fun onDateChosen(datePickerDate: DatePickerDate) {
        datePickerLiveData.setValue(datePickerDate)
    }
}