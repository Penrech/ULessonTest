package com.enrech.ulessontest.common_data.di

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import com.enrech.ulessontest.common_data.provider.DispatcherProviderImpl
import com.enrech.ulessontest.common_data.provider.HttpClientProvider
import com.enrech.ulessontest.common_data.provider.UrlProviderImpl
import com.enrech.ulessontest.common_data.repository.CacheDbRepositoryImpl
import com.enrech.ulessontest.common_data.utils.ApplicationLifeCycle
import com.enrech.ulessontest.common_domain.DriverFactory
import com.enrech.ulessontest.common_domain.ULessonCacheDb
import com.enrech.ulessontest.common_domain.provider.DispatcherProvider
import com.enrech.ulessontest.common_domain.provider.UrlProvider
import com.enrech.ulessontest.common_domain.repository.CacheDbRepository
import comenrechulessontestcommondomain.sqldelight.ChapterDto
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonDataModule {
    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(impl: DispatcherProviderImpl): DispatcherProvider

    @Binds
    @Singleton
    abstract fun bindUrlProvider(impl: UrlProviderImpl): UrlProvider

    @Binds
    @Singleton
    abstract fun bindCacheDbRepository(impl: CacheDbRepositoryImpl): CacheDbRepository

    companion object {
        @Provides
        @ApplicationLifeCycle
        fun provideAppLifeCycleOwner(): LifecycleOwner = ProcessLifecycleOwner.get()

        @Provides
        @Singleton
        fun provideHttpClient(): HttpClient = HttpClientProvider.getClient()

        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext context: Context): ULessonCacheDb =
            ULessonCacheDb(
                driver = DriverFactory(context).createDriver(),
                chapterDtoAdapter = ChapterDto.Adapter(orderPositionAdapter = IntColumnAdapter)
            )
    }
}