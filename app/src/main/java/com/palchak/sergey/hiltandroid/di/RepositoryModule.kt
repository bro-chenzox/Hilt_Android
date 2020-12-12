package com.palchak.sergey.hiltandroid.di

import androidx.annotation.MainThread
import com.palchak.sergey.hiltandroid.repository.MainRepository
import com.palchak.sergey.hiltandroid.retrofit.BlogRetrofit
import com.palchak.sergey.hiltandroid.retrofit.NetworkMapper
import com.palchak.sergey.hiltandroid.room.BlogDao
import com.palchak.sergey.hiltandroid.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, blogRetrofit, cacheMapper, networkMapper)
    }
}