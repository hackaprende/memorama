package com.hackaprende.hackamemory.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.hackaprende.hackamemory.R
import com.hackaprende.hackamemory.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    companion object {
        const val GAME_SIZE_KEY = "game_size"
        const val GAME_SIZE_EASY = 0
        const val GAME_SIZE_NORMAL = 1
        const val GAME_SIZE_HARD = 2
        const val GAME_SIZE_VERY_HARD = 3

        private const val NUMBER_OF_COLUMNS_EASY = 4
        private const val NUMBER_OF_COLUMNS_NORMAL = 2
        private const val NUMBER_OF_COLUMNS_HARD = 4
        private const val NUMBER_OF_COLUMNS_VERY_HARD = 5

        private const val NUMBER_OF_ROWS_EASY = 3
        private const val NUMBER_OF_ROWS_NORMAL = 5
        private const val NUMBER_OF_ROWS_HARD = 4
        private const val NUMBER_OF_ROWS_VERY_HARD = 4
    }

    private var gameCards = mutableListOf<MemoryCard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameRecycler = binding.recycler

        val gridLayoutManager = GridLayoutManager(this, getColumnSize())
        gameRecycler.layoutManager = gridLayoutManager
        gameRecycler.setHasFixedSize(true)
        val cardAdapter = CardAdapter {
            it.isChecked = true
            
        }
        gameRecycler.adapter = cardAdapter
        gameCards = buildCardsForGame()
        cardAdapter.submitList(gameCards)
    }

    private fun getColumnSize(): Int {
        // Game is easy by default
        return when (intent.extras?.getInt(GAME_SIZE_KEY) ?: 0) {
            GAME_SIZE_EASY -> NUMBER_OF_COLUMNS_EASY
            GAME_SIZE_NORMAL -> NUMBER_OF_COLUMNS_NORMAL
            GAME_SIZE_HARD -> NUMBER_OF_COLUMNS_HARD
            GAME_SIZE_VERY_HARD -> NUMBER_OF_COLUMNS_VERY_HARD
            else -> NUMBER_OF_COLUMNS_EASY
        }
    }

    private fun getRowSize(): Int {
        return when (intent.extras?.getInt(GAME_SIZE_KEY) ?: 0) {
            GAME_SIZE_EASY -> NUMBER_OF_ROWS_EASY
            GAME_SIZE_NORMAL -> NUMBER_OF_ROWS_NORMAL
            GAME_SIZE_HARD -> NUMBER_OF_ROWS_HARD
            GAME_SIZE_VERY_HARD -> NUMBER_OF_ROWS_VERY_HARD
            else -> NUMBER_OF_ROWS_EASY
        }
    }

    private fun buildCardsForGame(): MutableList<MemoryCard> {
        val allCards = getAllCards()
        val cardList = mutableListOf<MemoryCard>()
        val numberOfRows = getRowSize()

        var i = 0
        while (i < numberOfRows * getColumnSize()) {
            cardList.add(allCards[0])
            i++
        }

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