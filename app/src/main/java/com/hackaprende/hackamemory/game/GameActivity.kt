package com.hackaprende.hackamemory.game

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.hackaprende.hackamemory.R
import com.hackaprende.hackamemory.databinding.ActivityGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

    companion object {
        const val GAME_SIZE_KEY = "game_size"
        const val GAME_DIFFICULTY_EASY = 0
        const val GAME_DIFFICULTY_NORMAL = 1
        const val GAME_DIFFICULTY_HARD = 2
        const val GAME_DIFFICULTY_VERY_HARD = 3
    }

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backGameButton.setOnClickListener {
            onBackPressed()
        }

        val gameDifficulty = intent.extras?.getInt(GAME_SIZE_KEY, GAME_DIFFICULTY_EASY)

        val gameRecycler = binding.recycler

        val gridLayoutManager = GridLayoutManager(this,
            gameViewModel.getColumnSize(gameDifficulty))
        gameRecycler.layoutManager = gridLayoutManager
        gameRecycler.setHasFixedSize(true)
        val cardAdapter = CardAdapter()
        gameRecycler.adapter = cardAdapter

        gameViewModel.gameCards.observe(this) {
            cardAdapter.submitList(it)
            cardAdapter.notifyDataSetChanged()
        }

        gameViewModel.gameWon.observe(this) {
            gameWon ->
            if (gameWon) {
                binding.confetti.playAnimation()
                binding.confetti.visibility = View.VISIBLE
                binding.sadAnimation.visibility = View.GONE
            }
        }

        gameViewModel.gameLost.observe(this) {
            gameLost ->
            if (gameLost) {
                binding.sadAnimation.playAnimation()
                binding.sadAnimation.visibility = View.VISIBLE
                binding.confetti.visibility = View.GONE
                binding.blockingView.setOnClickListener(null)
            }
        }

        gameViewModel.totalAttempts.observe(this) {
            binding.totalAttempts.text = getString(R.string.attempts_format, it)
        }

        cardAdapter.setOnItemClickListener(object : CardAdapter.OnItemClickListener {
            override fun onItemClicked(cardIndex: Int, card: MemoryCard) {
               gameViewModel.evaluateClickedCard(cardIndex, card)
            }
        })

        gameViewModel.prepareGame(gameDifficulty)
    }
}