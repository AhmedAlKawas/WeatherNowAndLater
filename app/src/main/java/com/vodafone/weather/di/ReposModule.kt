package com.vodafone.weather.di

import com.vodafone.domain.repos.CitiesRepo
import com.vodafone.data.repos.CitiesRepoImpl
import com.vodafone.domain.repos.CurrentCityWeatherRepo
import com.vodafone.data.repos.CurrentCityWeatherRepoImpl
import com.vodafone.data.data_sources.cities.CitiesDataSource
import com.vodafone.data.data_sources.weather.WeatherDataSource
import com.vodafone.domain.repos.ForeCastRepo
import com.vodafone.data.repos.ForeCastRepoImpl
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

    @Provides
    @Singleton
    fun provideWeatherRepo(
        weatherDataSource: WeatherDataSource,
        citiesDataSource: CitiesDataSource
    ): CurrentCityWeatherRepo =
        CurrentCityWeatherRepoImpl(
            weatherDataSource = weatherDataSource,
            citiesDataSource = citiesDataSource
        )

    @Provides
    @Singleton
    fun provideForeCastRepo(
        weatherDataSource: WeatherDataSource,
        citiesDataSource: CitiesDataSource
    ): ForeCastRepo =
        ForeCastRepoImpl(
            weatherDataSource = weatherDataSource,
            citiesDataSource = citiesDataSource
        )

}