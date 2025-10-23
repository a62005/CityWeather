package com.example.lib_database

import android.content.Context
import androidx.startup.Initializer
import org.koin.core.context.loadKoinModules

class DatabaseModuleInitializer : Initializer<String> {

    companion object {
        private const val TAG = "DatabaseModuleInitializer"
    }


    override fun create(context: Context): String {
        loadKoinModules(databaseModule)
        return TAG
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        return emptyList()
    }
}