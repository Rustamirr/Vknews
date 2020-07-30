package com.example.vknews.presentation.mainactivity

import com.example.vknews.domain.main.MainModule
import com.example.vknews.presentation.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import ru.terrakok.cicerone.Navigator

@Module(includes = [MainModule::class])
interface MainActivityModule {

    @Binds
    fun bindNavigator(navigator: NavigatorImpl): Navigator
}