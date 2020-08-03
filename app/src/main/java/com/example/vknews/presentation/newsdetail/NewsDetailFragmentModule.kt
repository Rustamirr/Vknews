package com.example.vknews.presentation.newsdetail

import com.example.vknews.domain.newsdetail.NewsDetailModule
import dagger.Module

@Module(includes = [NewsDetailModule::class])
interface NewsDetailFragmentModule