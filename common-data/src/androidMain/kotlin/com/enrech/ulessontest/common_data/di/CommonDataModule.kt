package com.enrech.ulessontest.common_data.di

import com.enrech.ulessontest.common_data.provider.DispatcherProviderImpl
import com.enrech.ulessontest.common_domain.provider.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonDataModule {
    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(impl: DispatcherProviderImpl): DispatcherProvider
}