package com.example.aa1_hangedman

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.Level
import models.LevelAdapter

class ScrollActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scroll)

        val levels = listOf(
            Level("Palabra: PauG", "Letras: 4", R.drawable.easy),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
            Level("Palabra: AugustusSolInvictus", "Letras: 19", R.drawable.hard),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
            Level("Palabra: Gemix", "Letras: 5", R.drawable.medium),
        )

        val recyclerView: RecyclerView = findViewById(R.id.level_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LevelAdapter(levels)

    }
}