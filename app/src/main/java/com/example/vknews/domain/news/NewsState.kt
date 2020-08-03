package com.example.vknews.domain.news

import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

data class NewsState(
    val pageKey: String? = null,
    val startDate: LocalDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()),
    val endDate: LocalDate = LocalDate.now()
)