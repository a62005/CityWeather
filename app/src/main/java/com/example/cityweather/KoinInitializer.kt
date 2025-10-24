package com.example.cityweather

import android.content.Context
import androidx.startup.Initializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinInitializer: Initializer<String> {

    companion object {
        private const val TAG = "KoinInitializer"
    }

    override fun create(context: Context): String {
        startKoin {
            androidContext(context)
        }
        return TAG
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}