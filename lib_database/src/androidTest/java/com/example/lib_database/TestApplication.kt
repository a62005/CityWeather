package com.example.lib_database

import android.app.Application
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            modules(testModuleList)
        }
    }

    private val testModuleList = listOf(
        module {
            single {
                ONDataBase.invokeTestDatabase(get())
            }
        },
        daoModule
    )
}