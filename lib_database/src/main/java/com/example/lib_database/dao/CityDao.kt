package com.example.lib_database.dao

import androidx.room.Dao
import com.example.lib_database.entities.CityBean

@Dao
abstract class CityDao : BaseDao<CityBean>()