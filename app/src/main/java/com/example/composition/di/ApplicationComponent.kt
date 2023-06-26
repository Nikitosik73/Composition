package com.example.composition.di

import android.app.Application
import com.example.composition.di.annotation.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class
    ]
)
interface ApplicationComponent {

    fun fragmentGameFactory(): FragmentComponent.GameFactory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}