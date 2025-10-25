package com.example.lib_database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.lib_database.entities.CityBean
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CityDao : BaseDao<CityBean>() {

    @Query("SELECT * FROM CityBean WHERE countryCode = :countryCode")
    abstract suspend fun getCityByCountryCode(countryCode: String): CityBean?

    @Query("SELECT * FROM CityBean WHERE countryCode = :countryCode LIMIT 1")
    abstract fun observeCityByCountryCode(countryCode: String): Flow<CityBean?>

    @Query("SELECT COUNT(*) FROM CityBean")
    abstract suspend fun getSize(): Int

    @Query("SELECT * FROM CityBean ORDER BY city")
    abstract fun observeCity(): Flow<List<CityBean>>

    suspend fun isEmpty(): Boolean {
        return getSize() == 0
    }

}