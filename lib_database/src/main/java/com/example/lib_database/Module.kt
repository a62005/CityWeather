package com.example.lib_database

import org.koin.core.module.Module
import org.koin.dsl.module

internal val daoModule = module {
    factory { get<ONDataBase>().cityDao() }
}

internal val databaseModule: List<Module> = listOf(module {
    single { ONDataBase.invoke(context = get()) }
}, daoModule)