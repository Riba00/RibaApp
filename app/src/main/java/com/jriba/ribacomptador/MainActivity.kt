package com.jriba.ribacomptador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private val INITIAL_TIME = 10
    private val TAG = MainActivity::class.java.simpleName

    internal lateinit var tapMeButton: Button
    internal lateinit var timeTextView: TextView
    internal lateinit var counterTextView: TextView
    internal lateinit var countDownTimer: CountDownTimer

    internal var counter = 0
    internal var time = INITIAL_TIME

    internal var appStarted = false
    internal val initialCountDownTimer: Long = time.toLong() * 1000
    internal val internalCountDownTimer: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Hola mon")

        Log.d(TAG, counter.toString())
        Log.d(TAG, time.toString())

        initCountdown()

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.counterTextView)

        tapMeButton.setOnClickListener { view ->
            val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
            view.startAnimation(bounceAnimation)
            if (!appStarted) {
                startGame()
            }
            incrementCounter()
        }
        timeTextView.text = getString(R.string.timeText, time)
    }

    private fun startGame() {
        countDownTimer.start()
        appStarted = true
    }

    private fun initCountdown() {
        countDownTimer = object : CountDownTimer(initialCountDownTimer, internalCountDownTimer) {
            override fun onTick(p0: Long) {
                val timeLeft = p0 / 1000
                timeTextView.text = timeLeft.toString()
            }

            override fun onFinish() {
                endGame()
            }
        }
    }


    private fun incrementCounter() {
        if (!appStarted) {
            startGame()
        }
        val blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink)

        counter += 1

        val newCounter = getString(R.string.puntuacio, counter)
        counterTextView.text = newCounter
        counterTextView.startAnimation(blinkAnimation)
    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.endGame, counter), Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun resetGame() {
        counter = 0
        counterTextView.text = counter.toString()

        time = INITIAL_TIME
        timeTextView.text = time.toString()
        initCountdown()

        appStarted = false

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionAbout) {
            showInfo()
        }
        return true
    }

    private fun showInfo() {

        val dialogTitle = getString(R.string.aboutTitle, BuildConfig.VERSION_NAME)
        val dialogMessage = getString(R.string.aboutMessage)

        val builder = AlertDialog.Builder(this)

        builder.setTitle(dialogTitle).setMessage(dialogMessage).create().show()
    }
}