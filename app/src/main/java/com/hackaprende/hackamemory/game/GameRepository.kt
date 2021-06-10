package com.hackaprende.hackamemory.game

import com.hackaprende.hackamemory.R

class GameRepository {

    fun getCardsForGame(numberOfRows: Int, numberOfColumns: Int): MutableList<MemoryCard> {
        val allCards = getAllCards()

        val cardList = allCards.subList(0, numberOfRows * numberOfColumns)
        cardList.shuffle()
        return cardList
    }

    private fun getAllCards(): MutableList<MemoryCard> {
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