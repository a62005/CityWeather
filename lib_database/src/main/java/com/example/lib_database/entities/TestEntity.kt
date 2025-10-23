package com.example.lib_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TestEntity")
data class TestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)