package com.nativeteams.common.data.di

import com.nativeteams.common.data.repository.StocksRepositoryImpl
import com.nativeteams.common.domain.repository.StocksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindStocksRepository(impl: StocksRepositoryImpl): StocksRepository
}