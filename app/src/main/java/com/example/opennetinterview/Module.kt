package com.example.opennetinterview

import com.example.opennetinterview.repo.LaunchRepository
import com.example.opennetinterview.repo.MainRepository
import com.example.opennetinterview.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

private val viewModelModule = module {
    viewModelOf(::MainViewModel)
}

private val repoModule = module {
    factoryOf(::LaunchRepository)
    factoryOf(::MainRepository)
}

val mainModule = listOf(module {
    factory { CoroutineScope(Dispatchers.IO) }
}, viewModelModule, repoModule)

