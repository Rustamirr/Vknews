package com.example.vknews.data.authorization

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AuthorizationDataModule {

    @Singleton
    @Binds
    fun bindTokenSource(tokenSourceImpl: TokenSourceImpl): TokenSource
}