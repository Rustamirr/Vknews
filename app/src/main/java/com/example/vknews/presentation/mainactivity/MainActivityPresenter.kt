package com.example.vknews.presentation.mainactivity

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter
@Inject constructor(
        private val router: Router
) : MvpPresenter<MainActivityView>() {

}