package com.example.aa1_hangedman

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HangManActivity : AppCompatActivity() {

    private var word: String = ""
    private lateinit var hangmanToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hang_man)

        initializeHangman()

        hangmanToolbar = findViewById(R.id.HangmanToolbar)
        setSupportActionBar(hangmanToolbar)
    }

    fun initializeHangman()
    {
        val intent = intent;
        word = intent.getStringExtra("level_word") ?: ""
        val underlinedWord = word.replace(Regex("."), "_ ")

        val hangmanWord: TextView = findViewById(R.id.HagmanWord)
        hangmanWord.text = underlinedWord
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