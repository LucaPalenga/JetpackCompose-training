package com.example.jcex.accessibility

import android.app.Application
import com.example.jcex.accessibility.data.AppContainer
import com.example.jcex.accessibility.data.AppContainerImpl


class JetnewsApplication : Application() {

    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}