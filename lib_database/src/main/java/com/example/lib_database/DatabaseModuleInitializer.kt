package com.example.lib_database

import android.content.Context
import androidx.startup.Initializer
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

class DatabaseModuleInitializer : Initializer<String> {

    companion object {
        private const val TAG = "DatabaseModuleInitializer"
    }


    override fun create(context: Context): String {
        loadKoinModules(moduleList)
        return TAG
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        return emptyList()
    }

    private val daoModule = module {
        factory { get<ONDataBase>().cityDao() }
    }

    private val moduleList: List<Module> = listOf(module {
        single { ONDataBase.invoke(context = get()) }
    }, daoModule)
}