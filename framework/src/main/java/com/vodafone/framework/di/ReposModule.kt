package com.vodafone.framework.di

import com.vodafone.data.data_sources.CitiesDataSource
import com.vodafone.data.repos.citiies.CitiesRepo
import com.vodafone.data.repos.citiies.CitiesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReposModule {

    @Provides
    @Singleton
    fun provideCitiesRepo(citiesDataSource: CitiesDataSource): CitiesRepo =
        CitiesRepoImpl(citiesDataSource = citiesDataSource)

}