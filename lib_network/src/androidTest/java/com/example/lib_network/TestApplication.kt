package com.example.lib_network

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            modules(networkModule)
        }
    }
}