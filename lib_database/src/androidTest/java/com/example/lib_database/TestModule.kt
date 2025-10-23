package com.example.lib_database

import androidx.test.platform.app.InstrumentationRegistry
import org.koin.dsl.module

val testRoomModule = module {
    single {
        ONDataBase.invokeTestDatabase(InstrumentationRegistry.getInstrumentation().targetContext)
    }
}

val testModuleList = listOf(
    testRoomModule
)
