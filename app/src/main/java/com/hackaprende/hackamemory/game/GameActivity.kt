package com.hackaprende.hackamemory.game

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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
    private var wonCards = mutableSetOf<MemoryCard>()

    private var cardsShown = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameRecycler = binding.recycler

        val gridLayoutManager = GridLayoutManager(this, getColumnSize())
        gameRecycler.layoutManager = gridLayoutManager
        gameRecycler.setHasFixedSize(true)
        val cardAdapter = CardAdapter()

        cardAdapter.setOnItemClickListener(object : CardAdapter.OnItemClickListener {
            override fun onItemClicked(cardIndex: Int, card: MemoryCard) {
                cardsShown++
                gameCards[cardIndex].isChecked = true
                cardAdapter.notifyItemChanged(cardIndex)

                if (cardsShown == 2) {
                    if (cardIsPair(card)) {
                        wonCards.add(card)
                    } else {
                        val handler = Handler(Looper.getMainLooper())
                        val runnable = Runnable {
                            unCheckNotWonCards()
                            cardAdapter.notifyDataSetChanged()
                        }
                        handler.postDelayed(runnable, 1000)
                    }
                    cardsShown = 0
                }
            }
        })

        gameRecycler.adapter = cardAdapter
        gameCards = buildCardsForGame()
        cardAdapter.submitList(gameCards)
    }

    private fun unCheckNotWonCards() {
        for (gameCard in gameCards) {
            if (!wonCards.contains(gameCard)) {
                gameCard.isChecked = false
            }
        }
    }

    private fun cardIsPair(card: MemoryCard): Boolean {
        var checkedCount = 0

        for (gameCard in gameCards) {
            if (gameCard.imageId == card.imageId && gameCard.isChecked) {
                checkedCount++
            }
        }

        return checkedCount == 2
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
        val numberOfRows = getRowSize()

        val cardList = allCards.subList(0, numberOfRows * getColumnSize())
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