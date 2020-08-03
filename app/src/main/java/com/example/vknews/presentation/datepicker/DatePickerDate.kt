package com.example.vknews.presentation.datepicker

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
data class DatePickerDate(
    val date: LocalDate,
    val dateType: DatePickerDateType
) : Parcelable

enum class DatePickerDateType {
    START_DATE,
    END_DATE
}

