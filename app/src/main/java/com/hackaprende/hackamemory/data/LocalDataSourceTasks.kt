package com.hackaprende.hackamemory.data

import com.hackaprende.hackamemory.game.MemoryCard

interface LocalDataSourceTasks {

    fun getCardsForGame(numberOfRows: Int, numberOfColumns: Int): MutableList<MemoryCard>
}