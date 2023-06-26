package com.example.composition.app

import android.app.Application
import com.example.composition.di.DaggerApplicationComponent

class GameApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}