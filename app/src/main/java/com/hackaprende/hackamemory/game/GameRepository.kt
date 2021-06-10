package com.hackaprende.hackamemory.game

import com.hackaprende.hackamemory.data.LocalDataSource

class GameRepository {

    fun getCardsForGame(numberOfRows: Int, numberOfColumns: Int): MutableList<MemoryCard> {
        return LocalDataSource().getCardsForGame(numberOfRows, numberOfColumns)
    }
}