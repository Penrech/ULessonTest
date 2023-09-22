package com.enrech.ulessontest.ulesson_data.di

import com.enrech.ulessontest.ulesson_data.repository.ContentRepositoryImpl
import com.enrech.ulessontest.ulesson_domain.repository.ContentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UlessonDataModule {

    @Binds
    @Singleton
    abstract fun bindContentRepository(impl: ContentRepositoryImpl): ContentRepository
}