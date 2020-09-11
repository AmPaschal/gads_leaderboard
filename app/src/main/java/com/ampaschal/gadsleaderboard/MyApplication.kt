package com.ampaschal.gadsleaderboard

import android.app.Application
import com.ampaschal.gadsleaderboard.koin.appModule
import com.ampaschal.gadsleaderboard.koin.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(appModule, networkModule))
        }
    }
}