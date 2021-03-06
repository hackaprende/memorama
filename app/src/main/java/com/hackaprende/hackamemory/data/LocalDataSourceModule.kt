package com.hackaprende.hackamemory.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun getLocalDataSourceTasks(localDataSource: LocalDataSource): LocalDataSourceTasks
}