package com.hackaprende.hackamemory.game

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GameTasksModule {

    @Binds
    abstract fun getGameTasks(gameRepository: GameRepository): GameTasks
}