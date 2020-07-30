package com.example.vknews.domain.news

import com.example.vknews.domain.core.Interactor
import java.time.LocalDate

interface NewsInteractor : Interactor<NewsState> {

    fun setStartDate(startDate: LocalDate)

    fun setEndDate(endDate: LocalDate)
}