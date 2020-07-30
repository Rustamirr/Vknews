package com.example.vknews.presentation.mainactivity

import com.example.vknews.data.authorization.Token
import com.example.vknews.domain.core.Logger
import com.example.vknews.domain.core.Schedulers
import com.example.vknews.domain.main.MainInteractor
import com.example.vknews.presentation.navigation.Screen
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter
@Inject constructor(
    private val router: Router,
    private val interactor: MainInteractor,
    private val logger: Logger,
    private val schedulers: Schedulers
) : MvpPresenter<MainActivityView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(Screen.News)
    }

    fun onLogin(token: Token) {
        compositeDisposable.add(
            interactor.loginPassed(token)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribeBy(onError = logger::logError)
        )
    }

    fun onLoginFailed() {
        // TODO: login failed
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}