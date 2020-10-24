package com.decimalab.photomemory

import android.app.Application
import com.decimalab.photomemory.networking.RemoteApi
import com.decimalab.photomemory.networking.buildApiService

class App : Application() {

    companion object {
        private lateinit var instance: App

        private val apiService by lazy { buildApiService() }

        val remoteApi by lazy { RemoteApi(apiService) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}