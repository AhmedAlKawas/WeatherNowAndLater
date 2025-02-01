package com.vodafone.weather.di

import android.content.Context
import androidx.room.Room
import com.vodafone.data.room_db.AppDataBase
import com.vodafone.data.room_db.daos.CitiesDao
import com.vodafone.data.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext app: Context): AppDataBase {
        return Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCitiesDao(appDataBase: AppDataBase): CitiesDao {
        return appDataBase.citiesDao()
    }

}