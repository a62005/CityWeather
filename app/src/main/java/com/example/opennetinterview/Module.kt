package com.example.opennetinterview

import com.example.opennetinterview.repo.LaunchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

private val viewModelModule = module {
}

private val repoModule = module {
    factoryOf(::LaunchRepository)
}

val mainModule = listOf(module {
    factory { CoroutineScope(Dispatchers.IO) }
}, viewModelModule, repoModule)

