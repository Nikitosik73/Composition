package com.example.composition.di

import com.example.composition.data.GameRepositoryImpl
import com.example.composition.di.annotation.ApplicationScope
import com.example.composition.domain.repository.GameRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindGameRepository(
        impl: GameRepositoryImpl
    ): GameRepository
}
