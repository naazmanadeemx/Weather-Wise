package com.example.imadexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.*
import android.util.Log

class MainActivity : AppCompatActivity() {

    // Arrays to store weather data
    private val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val minTemps = IntArray(7) { 0 } // Initialized with default value 0
    private val maxTemps = IntArray(7) { 0 } // Initialized with default value 0
    private val conditions = arrayOfNulls<String>(7) // Initialized with null values

    private lateinit var spinnerDay: Spinner
    private lateinit var editTextMin: EditText
    private lateinit var editTextMax: EditText
    private lateinit var editTextCondition: EditText
    private lateinit var textViewAverageTemp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        spinnerDay = findViewById(R.id.spinnerDay)
        editTextMin = findViewById(R.id.editTextMin)
        editTextMax = findViewById(R.id.editTextMax)
        editTextCondition = findViewById(R.id.editTextWCondition)
        textViewAverageTemp = findViewById(R.id.textViewAverageTemp)

        // Setting up spinner with days
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDay.adapter = adapter

        // Set click listeners for buttons
        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            Log.i("MainActivity", "Add button clicked")
            addWeatherData()
        }

        findViewById<Button>(R.id.buttonViewDetails).setOnClickListener {
            Log.i("MainActivity", "View Details button clicked")
            viewDetails()
        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            Log.i("MainActivity", "Clear button clicked")
            clearData()
        }

        findViewById<Button>(R.id.buttonExitt).setOnClickListener {
            Log.i("MainActivity", "Exit button clicked")
            finish() // Finish the activity
        }
    }

    // Function to add weather data
    private fun addWeatherData() {
        Log.d("MainActivity", "Adding weather data")
        val day = spinnerDay.selectedItem.toString()
        val dayIndex = days.indexOf(day)
        val minTemp = editTextMin.text.toString().toIntOrNull()
        val maxTemp = editTextMax.text.toString().toIntOrNull()
        val condition = editTextCondition.text.toString()

        if (minTemp == null || maxTemp == null || condition.isBlank()) {
            Toast.makeText(this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show()
        } else {
            minTemps[dayIndex] = minTemp
            maxTemps[dayIndex] = maxTemp
            conditions[dayIndex] = condition
            Toast.makeText(this, "Data added and fields cleared.", Toast.LENGTH_SHORT).show()
            clearInputFields()
            calculateAverageTemperature()
        }
    }

    // Function to clear input fields
    private fun clearInputFields() {
        Log.d("MainActivity", "Clearing input fields")
        spinnerDay.setSelection(0)
        editTextMin.text.clear()
        editTextMax.text.clear()
        editTextCondition.text.clear()
    }

    // Function to clear all weather data
    private fun clearData() {
        Log.d("MainActivity", "Clearing all weather data")
        minTemps.fill(0)
        maxTemps.fill(0)
        conditions.fill(null)
        Toast.makeText(this, "Data cleared.", Toast.LENGTH_SHORT).show()
        clearInputFields()
        textViewAverageTemp.text = ""
    }

    // Function to calculate and display average temperature
    private fun calculateAverageTemperature() {
        Log.d("MainActivity", "Calculating average temperature")
        val validMinTemps = minTemps.filter { it != 0 }
        val validMaxTemps = maxTemps.filter { it != 0 }

        if (validMinTemps.isNotEmpty() && validMaxTemps.isNotEmpty()) {
            val totalTemp = validMinTemps.sum() + validMaxTemps.sum()
            val averageTemp = totalTemp / (validMinTemps.size + validMaxTemps.size)
            textViewAverageTemp.text = "Average Temperature: $averageTemp°C"
        } else {
            textViewAverageTemp.text = "Average Temperature: N/A"
        }
    }

    // Function to navigate to DetailedViewScreen
    private fun viewDetails() {
        Log.i("MainActivity", "Navigating to DetailedViewScreen")
        val intent = Intent(this, DetailedViewScreen::class.java).apply {
            putExtra("days", days)
            putExtra("minTemps", minTemps)
            putExtra("maxTemps", maxTemps)
            putExtra("conditions", conditions)
        }
        startActivity(intent)
    }
}