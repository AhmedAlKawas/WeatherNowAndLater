package com.vodafone.data.room_db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vodafone.core.models.CurrentCity

@Dao
interface CitiesDao {

    @Query("SELECT * FROM current_city LIMIT 1")
    suspend fun getCurrentCity(): CurrentCity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCurrentCity(currentCity: CurrentCity)

}