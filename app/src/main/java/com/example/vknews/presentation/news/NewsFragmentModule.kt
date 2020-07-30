package com.example.vknews.presentation.news

import com.example.vknews.domain.news.NewsModule
import dagger.Module

@Module(includes = [NewsModule::class])
interface NewsFragmentModule