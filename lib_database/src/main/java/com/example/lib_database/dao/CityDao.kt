package com.example.lib_database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.lib_database.entities.CityBean

@Dao
abstract class CityDao : BaseDao<CityBean>() {

    @Query("SELECT * FROM CityBean WHERE country = :country")
    abstract suspend fun getCityByCountry(country: String): CityBean?

    @Query("SELECT COUNT(*) FROM CityBean")
    abstract suspend fun getSize(): Int

}