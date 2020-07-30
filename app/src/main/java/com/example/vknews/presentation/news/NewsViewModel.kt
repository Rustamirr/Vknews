package com.example.vknews.presentation.news

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.vknews.presentation.core.SingleLiveEvent
import java.time.LocalDate

const val DATE_PICKER_DATE = "DATE"
const val DATE_PICKER_DATE_TYPE = "DATE_TYPE"

class NewsViewModel : ViewModel() {

    private val datePickerLiveData = SingleLiveEvent<Bundle>()

    fun observeDatePicker(): LiveData<Bundle> = datePickerLiveData

    fun onChooseDate(date: LocalDate, dateType: DateType) {
        datePickerLiveData.setValue(
            bundleOf(DATE_PICKER_DATE to date, DATE_PICKER_DATE_TYPE to dateType)
        )
    }
}