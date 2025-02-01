package com.vodafone.weather.di

import com.vodafone.data.data_sources.CitiesDataSource
import com.vodafone.city_input.repo.CitiesRepo
import com.vodafone.city_input.repo.CitiesRepoImpl
import com.vodafone.weather.repo.CurrentCityRepo
import com.vodafone.weather.repo.CurrentCityRepoImpl
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

    @Provides
    @Singleton
    fun provideCurrentCityRepo(citiesDataSource: CitiesDataSource): CurrentCityRepo =
        CurrentCityRepoImpl(citiesDataSource = citiesDataSource)

}