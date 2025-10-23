package com.example.lib_database

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.java.KoinJavaComponent.inject

@RunWith(AndroidJUnit4::class)
@LargeTest
class DatabaseTest {

    private val gameDB: ONDataBase by inject(ONDataBase::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun insert() {
        runBlocking {
        }
    }

    @After
    fun finish() {
        println("Done")
    }
}