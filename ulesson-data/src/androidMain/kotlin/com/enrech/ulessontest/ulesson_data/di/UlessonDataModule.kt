package com.enrech.ulessontest.ulesson_data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UlessonDataModule {

//    @Binds
//    @Singleton
//    abstract fun bindContentRepository(impl: ContentRepositoryImpl): ContentRepository
}