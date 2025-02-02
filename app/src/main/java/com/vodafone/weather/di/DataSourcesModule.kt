package com.vodafone.weather.di

import com.vodafone.data.data_sources.cities.CitiesDataSource
import com.vodafone.data.data_sources.cities.CitiesDataSourceImpl
import com.vodafone.data.data_sources.weather.WeatherDataSource
import com.vodafone.data.data_sources.weather.WeatherDataSourceImpl
import com.vodafone.data.room_db.daos.CitiesDao
import com.vodafone.data.services.CitiesServices
import com.vodafone.data.services.WeatherServices
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
    fun provideCitiesDataSource(
        citiesServices: CitiesServices,
        citiesDao: CitiesDao
    ): CitiesDataSource =
        CitiesDataSourceImpl(
            citiesServices = citiesServices,
            dao = citiesDao
        )

    @Provides
    @Singleton
    fun provideWeatherDataSource(weatherServices: WeatherServices): WeatherDataSource =
        WeatherDataSourceImpl(weatherServices = weatherServices)

}