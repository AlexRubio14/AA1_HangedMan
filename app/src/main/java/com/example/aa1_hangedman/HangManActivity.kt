package com.example.aa1_hangedman

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HangManActivity : AppCompatActivity() {

    private var word: String = ""
    private lateinit var hangmanToolbar: Toolbar
    private lateinit var buttonArray: Array<Button>
    private lateinit var currentWordState : CharArray
    private lateinit var hangmanWord: TextView
    private var currentImageIndex = 0
    private var maxErrors : Int = 7
    private lateinit var hangmanImage : List<Int>
    private lateinit var hangmangFinishButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hang_man)

        initializeHangman()
        initializeButtons()

        hangmanToolbar = findViewById(R.id.HangmanToolbar)
        setSupportActionBar(hangmanToolbar)
        buttonArray.forEach { button -> button.setOnClickListener{onButtonClick(button)} }

        hangmangFinishButton.setOnClickListener{finishHangman()}
    }

    fun initializeHangman()
    {
        val intent = intent;
        word = intent.getStringExtra("level_word") ?: ""
        val underlinedWord = word.replace(Regex("."), "_ ")

        hangmanWord = findViewById(R.id.HagmanWord)
        currentWordState = underlinedWord.toCharArray()
        updateWord()

        hangmanImage = listOf(
            R.drawable.hangmanwithouterror, R.drawable.hangmanwithoneerror,
            R.drawable.hangmanwithtwoerror, R.drawable.hangmanwiththreeerror,
            R.drawable.hangmanwithfourerror, R.drawable.hangmanwithfiveerror,
            R.drawable.hangmanwithsixerror, R.drawable.hangmanwithsevenerror
        )

        hangmangFinishButton = findViewById(R.id.hangmanButton)
        hangmangFinishButton.isClickable = false
    }

    fun initializeButtons()
    {
        buttonArray = arrayOf(
            findViewById(R.id.aButton),findViewById(R.id.bButton), findViewById(R.id.cButton),
            findViewById(R.id.dButton), findViewById(R.id.eButton), findViewById(R.id.fButton),
            findViewById(R.id.gButton), findViewById(R.id.hButton), findViewById(R.id.iButton),
            findViewById(R.id.jButton), findViewById(R.id.kButton), findViewById(R.id.lButton),
            findViewById(R.id.mButton), findViewById(R.id.nButton), findViewById(R.id.oButton),
            findViewById(R.id.pButton), findViewById(R.id.qButton), findViewById(R.id.rButton),
            findViewById(R.id.sButton), findViewById(R.id.tButton), findViewById(R.id.uButton),
            findViewById(R.id.vButton), findViewById(R.id.wButton), findViewById(R.id.xButton),
            findViewById(R.id.yButton), findViewById(R.id.zButton)
        )
    }

    fun onButtonClick(button: Button)
    {
        val letter = button.text.toString().uppercase()

        button.isEnabled = false;
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton))

        if(letter in word.uppercase())
        {
            for (i in word.indices)
            {
                if (word[i].toString().equals(letter,ignoreCase = true))
                {
                    currentWordState[i * 2] = letter[0]
                }

            }

            updateWord()
            winCondition()
        }
        else
        {
            currentImageIndex++
            if(currentImageIndex < maxErrors)
            {
                updateImage()
            }
            else
            {
                gameOver()
                updateImage()
            }
        }
    }

    fun gameOver()
    {
        buttonArray.forEach { button ->
            button.isEnabled = false
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton))
        }

        hangmanWord.text = getString(R.string.lose)
        hangmangFinishButton.isClickable = true

    }

    fun winCondition()
    {
        if(!currentWordState.contains('_'))
        {
            buttonArray.forEach { button ->
                button.isEnabled = false
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton))
            }
            hangmanWord.text = getString(R.string.win)
            hangmangFinishButton.isClickable = true
        }
    }

    fun finishHangman()
    {
        if(hangmangFinishButton.isClickable)
        {
            startActivity(Intent(this, ScrollActivity::class.java))
        }
    }


    fun updateImage()
    {
        val hangmangImageView = findViewById<ImageView>(R.id.HangmanImage)
        hangmangImageView.setImageResource(hangmanImage[currentImageIndex])
    }


    fun updateWord()
    {
        hangmanWord.text = currentWordState.joinToString(" ")
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