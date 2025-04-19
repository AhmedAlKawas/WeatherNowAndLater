package com.vodafone.data.room_db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vodafone.data.models.entities.CurrentCityEntity

@Dao
interface CitiesDao {

    @Query("SELECT * FROM current_city LIMIT 1")
    suspend fun getCurrentCity(): CurrentCityEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCurrentCity(currentCity: CurrentCityEntity)

}