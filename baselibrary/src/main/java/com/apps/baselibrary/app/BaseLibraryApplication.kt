package com.apps.baselibrary.app

import android.app.Application
import com.androidnetworking.AndroidNetworking

class BaseLibraryApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
    }
}