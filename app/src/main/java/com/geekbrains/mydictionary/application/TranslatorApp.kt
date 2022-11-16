package com.geekbrains.mydictionary.application

import android.app.Application
import com.geekbrains.mydictionary.di.application
import com.geekbrains.mydictionary.di.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}