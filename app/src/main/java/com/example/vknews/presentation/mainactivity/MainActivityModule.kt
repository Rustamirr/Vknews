package com.example.vknews.presentation.mainactivity

import com.example.vknews.presentation.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import ru.terrakok.cicerone.Navigator

@Module
interface MainActivityModule {

    @Binds
    fun bindNavigator(navigator: NavigatorImpl): Navigator
}