package com.vodafone.weather.di

import com.vodafone.city_input.repo.CitiesRepo
import com.vodafone.city_input.repo.CitiesRepoImpl
import com.vodafone.current_city.repo.CurrentCityWeatherRepo
import com.vodafone.current_city.repo.CurrentCityWeatherRepoImpl
import com.vodafone.data.data_sources.cities.CitiesDataSource
import com.vodafone.data.data_sources.weather.WeatherDataSource
import com.vodafone.forecast.repo.ForeCastRepo
import com.vodafone.forecast.repo.ForeCastRepoImpl
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