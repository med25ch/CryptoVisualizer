package com.resse.cryptovisualizer

import android.app.Application
import com.resse.cryptovisualizer.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CryptoVisualizerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@CryptoVisualizerApp)
            androidLogger()

            modules(appModule)
        }
    }
}