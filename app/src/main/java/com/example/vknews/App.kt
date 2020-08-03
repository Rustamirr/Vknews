package com.example.vknews

import android.content.Context
import androidx.multidex.MultiDex
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Singleton

class App : DaggerApplication() {

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