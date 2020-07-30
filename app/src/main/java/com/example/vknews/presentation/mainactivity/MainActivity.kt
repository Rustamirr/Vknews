package com.example.vknews.presentation.mainactivity

import android.content.Intent
import android.os.Bundle
import com.example.vknews.R
import com.example.vknews.data.authorization.Token
import com.example.vknews.presentation.core.baseMoxyPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : MvpAppCompatActivity(), HasAndroidInjector, MainActivityView {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var presenterProvider: Provider<MainActivityPresenter>

    private val presenter: MainActivityPresenter by baseMoxyPresenter(::presenterProvider::class.java.name) { presenterProvider.get() }

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VK.login(this, arrayListOf(VKScope.WALL, VKScope.FRIENDS))
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                presenter.onLogin(Token(token.accessToken))
            }

            override fun onLoginFailed(errorCode: Int) {
                presenter.onLoginFailed()
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}