package com.hackaprende.hackamemory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hackaprende.hackamemory.databinding.ActivityMainBinding
import com.hackaprende.hackamemory.game.GameActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons(binding)
    }

    private fun setupButtons(binding: ActivityMainBinding) {
        binding.easyButton.setOnClickListener {
            startGameActivity(GameActivity.GAME_DIFFICULTY_EASY)
        }

        binding.normalButton.setOnClickListener {
            startGameActivity(GameActivity.GAME_DIFFICULTY_NORMAL)
        }

        binding.hardButton.setOnClickListener {
            startGameActivity(GameActivity.GAME_DIFFICULTY_HARD)
        }

        binding.veryHardButton.setOnClickListener {
            startGameActivity(GameActivity.GAME_DIFFICULTY_VERY_HARD)
        }
    }

    private fun startGameActivity(gameSize: Int) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(GameActivity.GAME_SIZE_KEY, gameSize)
        startActivity(intent)
    }
}