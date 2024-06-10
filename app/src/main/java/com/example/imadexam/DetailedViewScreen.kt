package com.example.imadexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.util.Log

class DetailedViewScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view_screen)


        // Retrieve data passed from MainActivity
        val days = intent.getStringArrayExtra("days")
        val minTemps = intent.getIntArrayExtra("minTemps")
        val maxTemps = intent.getIntArrayExtra("maxTemps")
        val conditions = intent.getStringArrayExtra("conditions")

        // Initialize TextView to display detailed information
        val textViewDetails = findViewById<TextView>(R.id.textViewDetailss)

        // Build a string to display detailed information for each day
        val details = StringBuilder()
        if (days != null && minTemps != null && maxTemps != null && conditions != null) {
            for (i in days.indices) {
                // Append day, min temperature, max temperature, and weather condition
                details.append("Day: ${days[i]}, Min: ${minTemps[i]}, Max: ${maxTemps[i]}, Condition: ${conditions[i]}\n")
            }
        } else {
            // If data is not available, display a message
            details.append("No data available.")
        }

        // Set the text of the TextView to the built details string
        textViewDetails.text = details.toString()

        // Set onClickListener for the back button to finish the activity
        findViewById<Button>(R.id.buttonBackk).setOnClickListener {
            Log.d(TAG, "Back button clicked")
            finish()
        }
    }

    companion object {
        private const val TAG = "DetailedViewScreen"
    }
}