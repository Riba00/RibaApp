package com.jriba.ribacomptador

import android.content.IntentSender.OnFinished
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var timeTextView: TextView
    internal lateinit var counterTextView: TextView

    internal var counter = 0
    internal var time = 60

    internal var appStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDownTimer: Long = 6000
    internal val internalCountDownTimer: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    private fun endGame(){
        Toast.makeText(this, getString(R.string.endGame),Toast.LENGTH_LONG).show()

    }

    private fun resetGame(){


    }
}