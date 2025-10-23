package com.example.opennetinterview

import android.content.Context
import androidx.startup.Initializer
import com.example.lib_database.DatabaseModuleInitializer
import com.example.lib_network.NetworkModuleInitializer
import com.example.opennetinterview.repo.LaunchRepository
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules

class MainInitializer: Initializer<String> {

    companion object {
        private const val TAG = "MainInitializer"
    }

    override fun create(context: Context): String {
        loadKoinModules(mainModule)
        val launchRepository = GlobalContext.get().get<LaunchRepository>()
        launchRepository.init()
        return TAG
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(
            KoinInitializer::class.java,
            DatabaseModuleInitializer::class.java,
            NetworkModuleInitializer::class.java
        )
    }
}