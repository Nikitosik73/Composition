package com.example.composition.di

import com.example.composition.di.annotation.TypeLevel
import com.example.composition.domain.entity.Level
import com.example.composition.presentation.GameFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ViewModelModule::class
    ]
)
interface FragmentComponent {

    fun inject(fragment: GameFragment)

    @Subcomponent.Factory
    interface GameFactory {

        fun create(
            @BindsInstance @TypeLevel level: Level
        ): FragmentComponent
    }
}