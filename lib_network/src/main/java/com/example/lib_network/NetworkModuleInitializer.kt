package com.example.lib_network

import android.content.Context
import androidx.startup.Initializer
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

class NetworkModuleInitializer : Initializer<String> {

    companion object {
        private const val TAG = "NetworkModuleInitializer"
    }


    override fun create(context: Context): String {
        loadKoinModules(module)
        return TAG
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        return emptyList()
    }

    private val module = module {
        factoryOf(::NetworkManager)
    }
}