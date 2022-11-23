package com.geekbrains.mydictionary.application

import android.app.Application
import com.geekbrains.mydictionary.di.application
import com.geekbrains.mydictionary.di.historyScreen
import com.geekbrains.mydictionary.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}