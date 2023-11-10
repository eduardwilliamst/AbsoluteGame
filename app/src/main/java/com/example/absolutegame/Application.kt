package com.example.absolutegame

import android.app.Application
import com.example.absolutegame.di.Provider

class Application: Application() {

    lateinit var provider: Provider

    override fun onCreate() {
        super.onCreate()

        provider = Provider(applicationContext)
    }
}