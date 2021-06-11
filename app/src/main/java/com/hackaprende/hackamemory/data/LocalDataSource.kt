package com.hackaprende.hackamemory.data

import com.hackaprende.hackamemory.R
import com.hackaprende.hackamemory.game.MemoryCard
import javax.inject.Inject

class LocalDataSource @Inject constructor(): LocalDataSourceTasks {

    override fun getCardsForGame(numberOfRows: Int, numberOfColumns: Int): MutableList<MemoryCard> {
        val allCards = generateAllCards()

        val cardList = allCards.subList(0, numberOfRows * numberOfColumns)
        cardList.shuffle()
        return cardList
    }

    private fun generateAllCards(): MutableList<MemoryCard> {
        val cardList = mutableListOf<MemoryCard>()
        cardList.add(MemoryCard(R.drawable.memory_bat_card_front))
        cardList.add(MemoryCard(R.drawable.memory_bat_card_front))
        cardList.add(MemoryCard(R.drawable.memory_cat_card_front))
        cardList.add(MemoryCard(R.drawable.memory_cat_card_front))
        cardList.add(MemoryCard(R.drawable.memory_cow_card_front))
        cardList.add(MemoryCard(R.drawable.memory_cow_card_front))
        cardList.add(MemoryCard(R.drawable.memory_dragon_card_front))
        cardList.add(MemoryCard(R.drawable.memory_dragon_card_front))
        cardList.add(MemoryCard(R.drawable.memory_garbage_man_card_front))
        cardList.add(MemoryCard(R.drawable.memory_garbage_man_card_front))
        cardList.add(MemoryCard(R.drawable.memory_ghost_dog_card_front))
        cardList.add(MemoryCard(R.drawable.memory_ghost_dog_card_front))
        cardList.add(MemoryCard(R.drawable.memory_hen_card_front))
        cardList.add(MemoryCard(R.drawable.memory_hen_card_front))
        cardList.add(MemoryCard(R.drawable.memory_horse_card_front))
        cardList.add(MemoryCard(R.drawable.memory_horse_card_front))
        cardList.add(MemoryCard(R.drawable.memory_pig_card_front))
        cardList.add(MemoryCard(R.drawable.memory_pig_card_front))
        cardList.add(MemoryCard(R.drawable.memory_spider_card_front))
        cardList.add(MemoryCard(R.drawable.memory_spider_card_front))
        return cardList
    }
}