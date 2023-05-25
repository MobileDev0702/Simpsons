package com.sample.simpsonsviewer

import android.app.Application
import com.sample.simpsonsviewer.modules.networkModule
import com.sample.simpsonsviewer.modules.repositoryModule
import com.sample.simpsonsviewer.modules.viewModelModule
import org.koin.core.context.startKoin

class SimpsonsCharacterViewerApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}