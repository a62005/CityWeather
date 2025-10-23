package com.example.opennetinterview

import android.content.Context
import androidx.startup.Initializer
import com.example.lib_database.DatabaseModuleInitializer

class MainInitializer: Initializer<String> {

    companion object {
        private const val TAG = "MainInitializer"
    }
    override fun create(context: Context): String {
        // TODO load data
        return TAG
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(
            DatabaseModuleInitializer::class.java
        )
    }
}