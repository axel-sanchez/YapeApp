package com.example.yapeapp.core

import android.app.Application
import com.example.yapeapp.di.component.ApplicationComponent
import com.example.yapeapp.di.component.DaggerApplicationComponent
import com.example.yapeapp.di.module.ApplicationModule

/**
 * @author Axel Sanchez
 */
class MyApplication: Application() {
    val component: ApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()
}