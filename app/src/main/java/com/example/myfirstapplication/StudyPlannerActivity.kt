package com.example.myfirstapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class StudyPlannerActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences
    private lateinit var mathStart: EditText
    private lateinit var mathEnd: EditText
    private lateinit var scienceStart: EditText
    private lateinit var scienceEnd: EditText
    private lateinit var historyStart: EditText
    private lateinit var historyEnd: EditText
    private lateinit var notes1: EditText
    private lateinit var notes2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_planner)
        preferences = getSharedPreferences("StudyPalPrefs", MODE_PRIVATE)

        mathStart = findViewById(R.id.mathStart)
        mathEnd = findViewById(R.id.mathEnd)
        scienceStart = findViewById(R.id.scienceStart)
        scienceEnd = findViewById(R.id.scienceEnd)
        historyStart = findViewById(R.id.historyStart)
        historyEnd = findViewById(R.id.historyEnd)
        notes1 = findViewById(R.id.notes1)
        notes2 = findViewById(R.id.notes2)

        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.homeButton).setOnClickListener {
            startActivity(Intent(this, HomePageActivity::class.java))
        }

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            saveData()
        }

        loadSavedData()
    }

    private fun saveData() {
        preferences.edit().apply {
            putString("mathStart", mathStart.text.toString())
            putString("mathEnd", mathEnd.text.toString())
            putString("scienceStart", scienceStart.text.toString())
            putString("scienceEnd", scienceEnd.text.toString())
            putString("historyStart", historyStart.text.toString())
            putString("historyEnd", historyEnd.text.toString())
            putString("notes1", notes1.text.toString())
            putString("notes2", notes2.text.toString())
        }.apply()
    }

    private fun loadSavedData() {
        mathStart.setText(preferences.getString("mathStart", ""))
        mathEnd.setText(preferences.getString("mathEnd", ""))
        scienceStart.setText(preferences.getString("scienceStart", ""))
        scienceEnd.setText(preferences.getString("scienceEnd", ""))
        historyStart.setText(preferences.getString("historyStart", ""))
        historyEnd.setText(preferences.getString("historyEnd", ""))
        notes1.setText(preferences.getString("notes1", ""))
        notes2.setText(preferences.getString("notes2", ""))
    }
}