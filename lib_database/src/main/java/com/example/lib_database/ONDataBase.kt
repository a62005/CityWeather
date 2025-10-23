package com.example.lib_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lib_database.dao.TestDao
import com.example.lib_database.entities.TestEntity

@Database(
    entities = [TestEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ONDataBase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: ONDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        fun invokeTestDatabase(context: Context) =
            Room.inMemoryDatabaseBuilder(context, ONDataBase::class.java)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, ONDataBase::class.java, "ONDataBase.db")
                .build()
    }

    abstract fun testDao(): TestDao
}