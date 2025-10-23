package com.example.lib_network

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val networkModule = listOf(
    module {
        factoryOf(::NetworkManager)
    }
)