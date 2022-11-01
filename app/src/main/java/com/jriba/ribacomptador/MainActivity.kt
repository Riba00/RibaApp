package com.jriba.ribacomptador

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val INITIAL_TIME = 3
    private val TAG = MainActivity::class.java.simpleName

    internal lateinit var tapMeButton: Button
    internal lateinit var timeTextView: TextView
    internal lateinit var counterTextView: TextView

    internal var counter = 0
    internal var time = INITIAL_TIME

    internal var appStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDownTimer: Long = time.toLong() * 1000
    internal val internalCountDownTimer: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG,"Hola mon")

        Log.d(TAG, counter.toString())
        Log.d(TAG, time.toString())

        initCountdown()

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.counterTextView)

        tapMeButton.setOnClickListener {
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

        counter += 1
        counterTextView.text = counter.toString()

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
}