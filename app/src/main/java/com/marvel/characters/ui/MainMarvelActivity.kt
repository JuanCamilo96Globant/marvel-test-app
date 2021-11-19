package com.marvel.characters.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marvel.characters.databinding.ActivityItemDetailBinding

class MainMarvelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

}