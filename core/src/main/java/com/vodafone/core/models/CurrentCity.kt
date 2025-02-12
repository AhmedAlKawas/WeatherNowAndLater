package com.vodafone.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_city")
data class CurrentCity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String,

    val state: String,

    val country: String?,

    val latitude: Double?,

    val longitude: Double?

)
