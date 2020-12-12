package com.palchak.sergey.hiltandroid.repository

import com.palchak.sergey.hiltandroid.model.Blog
import com.palchak.sergey.hiltandroid.retrofit.BlogRetrofit
import com.palchak.sergey.hiltandroid.retrofit.NetworkMapper
import com.palchak.sergey.hiltandroid.room.BlogDao
import com.palchak.sergey.hiltandroid.room.CacheMapper
import com.palchak.sergey.hiltandroid.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        try {
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}