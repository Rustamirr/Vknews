package com.example.vknews

import android.content.Context
import androidx.multidex.MultiDex
import com.example.vknews.data.authorization.TokenSource
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

class App : DaggerApplication() {

    @Inject
    lateinit var tokenSource: TokenSource

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        initLogger()
        VK.addTokenExpiredHandler(object : VKTokenExpiredHandler {
            override fun onTokenExpired() {
                tokenSource.clearToken()
            }
        })
    }

    private fun initLogger() {
        Timber.plant(Timber.DebugTree())
    }
}

@Singleton
@Component(modules = [ApplicationModule::class])
interface AppComponent : AndroidInjector<App> {

    @Suppress("DEPRECATION")
    @Component.Builder
    abstract class AppBuilder : AndroidInjector.Builder<App>()
}