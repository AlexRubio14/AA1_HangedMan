package com.example.aa1_hangedman

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.Level
import models.LevelAdapter

class ScrollActivity : AppCompatActivity(), LevelAdapter.OnButtonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scroll)

        val levels = listOf(
            Level("PauG"),
            Level("Gemix"),
            Level("AugustusSolInvictus"),
            Level("Gemix"),
            Level("Gemix"),
            Level("Gemix"),
            Level("Gemix"),
            Level("Gemix"),
            Level("Gemix"),
            Level("Gemix"),
            Level("Gemix"),
            Level("Gemix"),
            Level("Gemix"),
            Level("Gemix"),
            Level("Gemix"),
        )

        val recyclerView: RecyclerView = findViewById(R.id.level_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LevelAdapter(levels, this)

    }

    override fun onButtonClick(level: Level) {
        Toast.makeText(this, "Nivel seleccionado: ${level.word}", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, HangManActivity::class.java)
        intent.putExtra("level_word", level.word)
        startActivity(intent)
    }

}