package com.example.vknews.presentation.core

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import moxy.MvpDelegateHolder
import moxy.MvpPresenter
import moxy.ktx.MoxyKtxDelegate

private const val KEYBOARD_BEHAVIOR_FLAG = 0

fun <T : MvpPresenter<*>> MvpDelegateHolder.baseMoxyPresenter(
        name: String,
        factory: () -> T
): MoxyKtxDelegate<T> {
    return MoxyKtxDelegate(mvpDelegate, name, factory)
}

fun Fragment.hideKeyboard() {
    view?.let {
        with(requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager) {
            hideSoftInputFromWindow(it.windowToken, KEYBOARD_BEHAVIOR_FLAG)
        }
    }
}
