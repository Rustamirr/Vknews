package com.example.vknews.presentation.mainactivity

import android.os.Bundle
import com.example.vknews.R
import com.example.vknews.presentation.core.baseMoxyPresenter
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : MvpAppCompatActivity(), HasAndroidInjector, MainActivityView {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var presenterProvider: Provider<MainActivityPresenter>

    private val presenter: MainActivityPresenter by baseMoxyPresenter(::presenterProvider::class.java.name) { presenterProvider.get() }

    private val navigator = SupportAppNavigator(this, R.id.container)

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}