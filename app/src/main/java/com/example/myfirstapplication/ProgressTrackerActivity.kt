package com.example.myfirstapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProgressTrackerActivity : AppCompatActivity() {

    private lateinit var subjectSpinner: Spinner
    private lateinit var studyTimeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_tracker)

        subjectSpinner = findViewById(R.id.subjectSpinner)
        studyTimeText = findViewById(R.id.studyTimeText)

        findViewById<Button>(R.id.selectButton).setOnClickListener {
            displayStudyTime()
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

    private fun displayStudyTime() {
        val selectedSubject = subjectSpinner.selectedItem.toString()
        val studyTime = getSharedPreferences("StudyTimes", MODE_PRIVATE)
            .getLong(selectedSubject, 0)

        val minutes = studyTime / 60000
        val seconds = (studyTime / 1000) % 60
        studyTimeText.text = String.format("Time studied in %s: %02d:%02d", selectedSubject, minutes, seconds)
    }
}