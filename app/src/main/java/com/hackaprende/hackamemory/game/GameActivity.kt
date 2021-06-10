package com.hackaprende.hackamemory.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hackaprende.hackamemory.R
import com.hackaprende.hackamemory.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    companion object {
        const val GAME_SIZE_KEY = "game_size"
        const val GAME_SIZE_EASY = 0
        const val GAME_SIZE_NORMAL = 1
        const val GAME_SIZE_HARD = 2
        const val GAME_SIZE_VERY_HARD = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
    }
}