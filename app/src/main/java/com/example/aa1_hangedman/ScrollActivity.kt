package com.example.aa1_hangedman

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.Level
import models.LevelAdapter

class ScrollActivity : AppCompatActivity(), LevelAdapter.OnButtonClickListener {

    private lateinit var scrollToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scroll)

        createScrollInformation()

        scrollToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(scrollToolbar)

    }

    fun createScrollInformation()
    {
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
        val intent = Intent(this, HangManActivity::class.java)
        intent.putExtra("level_word", level.word)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_toolbar, menu)
        updateMenuIcon(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(item.itemId == R.id.picture)
        {
            changeDarkMode(item)
            true
        }
        else
        {
            super.onOptionsItemSelected(item)
        }
    }

    private fun changeDarkMode(item: MenuItem) {
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            item.isChecked = false
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            item.isChecked = true
        }
    }

    private fun updateMenuIcon(menu: Menu?) {
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        val menuItem = menu?.findItem(R.id.picture)
        menuItem?.isChecked = (currentMode == AppCompatDelegate.MODE_NIGHT_YES)
    }

}