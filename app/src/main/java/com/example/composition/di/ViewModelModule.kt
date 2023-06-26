package com.example.composition.di

import androidx.lifecycle.ViewModel
import com.example.composition.di.annotation.ViewModelKey
import com.example.composition.presentation.viewmodel.GameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    fun bindGameViewModel(
        impl: GameViewModel
    ): ViewModel
}