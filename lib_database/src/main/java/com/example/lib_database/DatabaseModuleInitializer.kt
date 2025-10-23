package com.example.lib_database

import android.content.Context
import androidx.startup.Initializer

class DatabaseModuleInitializer: Initializer<String> {

    companion object {
        private const val TAG = "DatabaseModuleInitializer"
    }


    override fun create(context: Context): String {
        return TAG
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        return emptyList()
    }
}