package com.hackaprende.hackamemory.game

interface GameTasks {
    fun getCardsForGame(numberOfRows: Int, numberOfColumns: Int): MutableList<MemoryCard>
}