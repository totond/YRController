package com.yanzhikai.controllerandroid

import android.app.Application
import kotlin.properties.Delegates

class GlobalApplication : Application() {

    companion object {
        private var context: GlobalApplication by Delegates.notNull()

        fun context() = context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}

