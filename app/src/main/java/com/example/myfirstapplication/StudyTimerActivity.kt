package com.example.myfirstapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudyTimerActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private var startTime = 0L
    private var elapsedTime = 0L
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
    private lateinit var subjectSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_timer)

        timerText = findViewById(R.id.timerText)
        subjectSpinner = findViewById(R.id.subjectSpinner)

        findViewById<Button>(R.id.startTimerButton).setOnClickListener {
            startTimer()
        }

        findViewById<Button>(R.id.stopTimerButton).setOnClickListener {
            stopTimer()
        }

        findViewById<Button>(R.id.saveTimerButton).setOnClickListener {
            saveTime()
        }

        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.homeButton).setOnClickListener {
            startActivity(Intent(this, HomePageActivity::class.java))
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.subject_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            subjectSpinner.adapter = adapter
        }
    }

    private fun startTimer() {
        startTime = System.currentTimeMillis() - elapsedTime
        runnable = object : Runnable {
            override fun run() {
                elapsedTime = System.currentTimeMillis() - startTime
                val seconds = (elapsedTime / 1000) % 60
                val minutes = elapsedTime / 60000
                timerText.text = String.format("%02d:%02d", minutes, seconds)
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }

    private fun stopTimer() {
        handler.removeCallbacks(runnable)
    }

    private fun saveTime() {
        val selectedSubject = subjectSpinner.selectedItem.toString()
        val currentStudyTime = getSharedPreferences("StudyTimes", MODE_PRIVATE)
            .getLong(selectedSubject, 0)

        getSharedPreferences("StudyTimes", MODE_PRIVATE)
            .edit()
            .putLong(selectedSubject, currentStudyTime + elapsedTime)
            .apply()

        elapsedTime = 0L // Reset elapsed time
        timerText.text = "00:00" // Reset timer text
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}