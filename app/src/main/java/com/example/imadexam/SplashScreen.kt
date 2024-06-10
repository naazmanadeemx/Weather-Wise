package com.example.imadexam


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.util.Log


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Set onClickListener for the Start button
        findViewById<Button>(R.id.buttonStart).setOnClickListener {
            Log.d(TAG, "Start button clicked")
            startMainActivity()
        }

        // Set onClickListener for the Exit button
        findViewById<Button>(R.id.buttonExit).setOnClickListener {
            Log.d(TAG, "Exit button clicked")
            finish()
        }
    }

    // Function to start MainActivity
    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val TAG = "SplashScreen" // Tag for logging
    }
}