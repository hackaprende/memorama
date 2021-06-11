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

private const val TIME_TO_WAIT = 1000L
private const val TOTAL_ATTEMPTS = 10

class GameViewModel: ViewModel() {
    private var cardsShown = 0

    private val _gameCards = MutableLiveData<MutableList<MemoryCard>>()
    val gameCards: LiveData<MutableList<MemoryCard>>
        get() = _gameCards

    private val _gameWon = MutableLiveData<Boolean>()
    val gameWon: LiveData<Boolean>
        get() = _gameWon

    private val _gameLost = MutableLiveData<Boolean>()
    val gameLost: LiveData<Boolean>
        get() = _gameLost

    private val _totalAttempts = MutableLiveData<Int>()
    val totalAttempts: LiveData<Int>
        get() = _totalAttempts

    private val wonCards = mutableListOf<MemoryCard>()

    private val repository = GameRepository()

    private var evaluatingClick = false

    init {
        _gameCards.value = mutableListOf()
        _gameWon.value = false
        _totalAttempts.value = TOTAL_ATTEMPTS
        _gameLost.value = false
    }

    fun prepareGame(gameDifficulty: Int?) {
        _gameCards.value = repository.getCardsForGame(getRowSize(gameDifficulty),
            getColumnSize(gameDifficulty))
    }

    fun evaluateClickedCard(cardIndex: Int, card: MemoryCard) {
        if (!evaluatingClick) {
            evaluatingClick = true
            val gameCardList = _gameCards.value!!
            gameCardList[cardIndex].isChecked = true
            _gameCards.value = gameCardList

            cardsShown++
            if (cardsShown == 2) {
                if (cardIsPair(card)) {
                    wonCards.add(card)
                    checkIfGameWon()
                    evaluatingClick = false
                } else {
                    _totalAttempts.value = _totalAttempts.value!! - 1

                    if (_totalAttempts.value!! <= 0) {
                        _gameLost.value = true
                    }

                    val handler = Handler(Looper.getMainLooper())
                    val runnable = Runnable {
                        uncheckNotWonCards()
                        evaluatingClick = false
                    }
                    handler.postDelayed(runnable, TIME_TO_WAIT)
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
            GameActivity.GAME_DIFFICULTY_EASY -> NUMBER_OF_COLUMNS_EASY
            GameActivity.GAME_DIFFICULTY_NORMAL -> NUMBER_OF_COLUMNS_NORMAL
            GameActivity.GAME_DIFFICULTY_HARD -> NUMBER_OF_COLUMNS_HARD
            GameActivity.GAME_DIFFICULTY_VERY_HARD -> NUMBER_OF_COLUMNS_VERY_HARD
            else -> NUMBER_OF_COLUMNS_EASY
        }
    }

    private fun getRowSize(gameDifficulty: Int? = 0): Int {
        return when (gameDifficulty) {
            GameActivity.GAME_DIFFICULTY_EASY -> NUMBER_OF_ROWS_EASY
            GameActivity.GAME_DIFFICULTY_NORMAL -> NUMBER_OF_ROWS_NORMAL
            GameActivity.GAME_DIFFICULTY_HARD -> NUMBER_OF_ROWS_HARD
            GameActivity.GAME_DIFFICULTY_VERY_HARD -> NUMBER_OF_ROWS_VERY_HARD
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