package com.example.lib_database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.lib_database.entities.WeatherBean

@Dao
abstract class WeatherDao: BaseDao<WeatherBean>() {

    @Query("DELETE FROM WeatherBean")
    abstract suspend fun deleteAll()
}