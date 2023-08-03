package com.example.simpleweather.app

import android.app.Application
import com.example.simpleweather.data.db.HistoryDatabase
import com.example.simpleweather.di.dataModule
import com.example.simpleweather.di.domainModule
import com.example.simpleweather.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    val historyDatabase: HistoryDatabase by lazy { HistoryDatabase.getDataBase(this) }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(listOf(dataModule, domainModule, presentationModule))
        }
    }
}