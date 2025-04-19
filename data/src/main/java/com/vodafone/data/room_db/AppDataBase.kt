package com.vodafone.data.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vodafone.data.models.entities.CurrentCityEntity
import com.vodafone.data.room_db.daos.CitiesDao

@Database(entities = [CurrentCityEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun citiesDao(): CitiesDao

}