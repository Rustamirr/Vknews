package com.example.vknews.presentation.core

import moxy.MvpDelegateHolder
import moxy.MvpPresenter
import moxy.ktx.MoxyKtxDelegate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val DATE_FORMAT = "dd.MM.yy"
private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"

fun <T : MvpPresenter<*>> MvpDelegateHolder.baseMoxyPresenter(
    name: String,
    factory: () -> T
): MoxyKtxDelegate<T> {
    return MoxyKtxDelegate(mvpDelegate, name, factory)
}

fun String.toLocalDate(): LocalDate =
    LocalDate.parse(this, DateTimeFormatter.ofPattern(DATE_FORMAT))

fun LocalDate.toStringFormat(): String = format(DateTimeFormatter.ofPattern(DATE_FORMAT))

fun LocalDateTime.toStringFormat(): String = format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))