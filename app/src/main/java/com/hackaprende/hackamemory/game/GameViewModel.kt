package com.hackaprende.hackamemory.game

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val NUMBER_OF_COLUMNS_EASY = 4
private const val NUMBER_OF_COLUMNS_NORMAL = 2
private const val NUMBER_OF_COLUMNS_HARD = 4
private const val NUMBER_OF_COLUMNS_VERY_HARD = 5

private const val NUMBER_OF_ROWS_EASY = 3
private const val NUMBER_OF_ROWS_NORMAL = 5
private const val NUMBER_OF_ROWS_HARD = 4
private const val NUMBER_OF_ROWS_VERY_HARD = 4

class GameViewModel: ViewModel() {
    private var cardsShown = 0

    private val _gameCards = MutableLiveData<MutableList<MemoryCard>>()
    val gameCards: LiveData<MutableList<MemoryCard>>
        get() = _gameCards

    private val _gameWon = MutableLiveData<Boolean>()
    val gameWon: LiveData<Boolean>
        get() = _gameWon

    private val _totalErrors = MutableLiveData<Int>()
    val totalErrors: LiveData<Int>
        get() = _totalErrors

    private val wonCards = mutableListOf<MemoryCard>()

    private val repository = GameRepository()

    private var evaluatingClick = false

    init {
        _gameCards.value = mutableListOf()
        _gameWon.value = false
        _totalErrors.value = 0
    }

    fun prepareGame(gameDifficulty: Int?) {
        _gameCards.value = repository.getCardsForGame(getRowSize(gameDifficulty),
            getColumnSize(gameDifficulty))
    }

    fun evaluateClickedCard(cardIndex: Int, card: MemoryCard) {
        if (!evaluatingClick) {
            evaluatingClick= true
            cardsShown++
            val gameCardList = _gameCards.value!!
            gameCardList[cardIndex].isChecked = true
            _gameCards.value = gameCardList

            if (cardsShown == 2) {
                if (cardIsPair(card)) {
                    wonCards.add(card)
                    checkIfGameWon()
                    evaluatingClick = false
                } else {
                    _totalErrors.value = _totalErrors.value!! + 1
                    val handler = Handler(Looper.getMainLooper())
                    val runnable = Runnable {
                        evaluatingClick = false
                        uncheckNotWonCards()
                    }
                    handler.postDelayed(runnable, 1000)
                }
                cardsShown = 0
            } else {
                evaluatingClick = false
            }
        }
    }

    private fun checkIfGameWon() {
        if (wonCards.size >= (_gameCards.value!!.size / 2)) {
            _gameWon.value = true
        }
    }

    fun getColumnSize(gameDifficulty: Int? = 0): Int {
        // Game is easy by default
        return when (gameDifficulty) {
            GameActivity.GAME_SIZE_EASY -> NUMBER_OF_COLUMNS_EASY
            GameActivity.GAME_SIZE_NORMAL -> NUMBER_OF_COLUMNS_NORMAL
            GameActivity.GAME_SIZE_HARD -> NUMBER_OF_COLUMNS_HARD
            GameActivity.GAME_SIZE_VERY_HARD -> NUMBER_OF_COLUMNS_VERY_HARD
            else -> NUMBER_OF_COLUMNS_EASY
        }
    }

    private fun getRowSize(gameDifficulty: Int? = 0): Int {
        return when (gameDifficulty) {
            GameActivity.GAME_SIZE_EASY -> NUMBER_OF_ROWS_EASY
            GameActivity.GAME_SIZE_NORMAL -> NUMBER_OF_ROWS_NORMAL
            GameActivity.GAME_SIZE_HARD -> NUMBER_OF_ROWS_HARD
            GameActivity.GAME_SIZE_VERY_HARD -> NUMBER_OF_ROWS_VERY_HARD
            else -> NUMBER_OF_ROWS_EASY
        }
    }

    private fun uncheckNotWonCards() {
        val gameCardList = _gameCards.value!!

        for (gameCard in gameCardList) {
            if (!wonCards.contains(gameCard)) {
                gameCard.isChecked = false
            }
        }

        _gameCards.value = gameCardList
    }

    private fun cardIsPair(card: MemoryCard): Boolean {
        var checkedCount = 0

        for (gameCard in _gameCards.value!!) {
            if (gameCard.imageId == card.imageId && gameCard.isChecked) {
                checkedCount++
            }
        }

        return checkedCount == 2
    }
}