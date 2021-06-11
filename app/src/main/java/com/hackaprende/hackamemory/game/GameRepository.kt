package com.hackaprende.hackamemory.game

import com.hackaprende.hackamemory.data.LocalDataSourceTasks
import javax.inject.Inject

class GameRepository @Inject constructor(private val localDataSource: LocalDataSourceTasks) :
    GameTasks {

    override fun getCardsForGame(numberOfRows: Int, numberOfColumns: Int): MutableList<MemoryCard> =
        localDataSource.getCardsForGame(numberOfRows, numberOfColumns)
}