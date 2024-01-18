package com.example.myfirstapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.studyTimerButton).setOnClickListener {
            startActivity(Intent(this, StudyTimerActivity::class.java))
        }

        findViewById<Button>(R.id.studyPlannerButton).setOnClickListener {
            startActivity(Intent(this, StudyPlannerActivity::class.java))
        }

        findViewById<Button>(R.id.progressTrackerButton).setOnClickListener {
            startActivity(Intent(this, ProgressTrackerActivity::class.java))
        }

        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}