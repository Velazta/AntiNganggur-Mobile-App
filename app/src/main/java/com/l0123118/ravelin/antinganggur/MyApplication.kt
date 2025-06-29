package com.l0123118.ravelin.antinganggur

import android.app.Application
import com.l0123118.ravelin.antinganggur.di.AppContainer

class MyApplication : Application () {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}