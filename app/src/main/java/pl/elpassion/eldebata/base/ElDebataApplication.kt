package pl.elpassion.eldebata.base;

import android.app.Application
import android.content.Context

class ElDebataApplication : Application() {
    companion object {
        lateinit var applicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        Companion.applicationContext = applicationContext
    }
}