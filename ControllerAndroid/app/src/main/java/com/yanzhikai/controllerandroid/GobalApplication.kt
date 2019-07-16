package com.yanzhikai.controllerandroid

import android.app.Application
import android.content.Context

class GobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        sContext = this
    }

    companion object {
        lateinit var sContext: Context
    }
}
