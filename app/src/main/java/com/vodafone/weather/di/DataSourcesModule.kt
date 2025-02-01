package com.vodafone.weather.di

import com.vodafone.data.data_sources.CitiesDataSource
import com.vodafone.data.data_sources.CitiesDataSourceImpl
import com.vodafone.data.services.CitiesServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    @Singleton
    fun provideCitiesDataSource(citiesServices: CitiesServices): CitiesDataSource =
        CitiesDataSourceImpl(citiesServices = citiesServices)

}