package com.example.unitconverterapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etInput = findViewById<EditText>(R.id.etInput)
        val spinnerConversion = findViewById<Spinner>(R.id.spinnerConversion)
        val btnConvert = findViewById<Button>(R.id.btnConvert)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        val conversions = arrayOf("Kilometers to Miles", "Kilograms to Pounds", "Meters to Feet")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, conversions)
        spinnerConversion.adapter = adapter

        btnConvert.setOnClickListener {
            val input = etInput.text.toString().trim()
            val value = input.toDoubleOrNull()

            if (value == null) {
                tvResult.text = "Please enter a valid number"
            } else {
                val type = spinnerConversion.selectedItem.toString()
                val result = when (type) {
                    "Kilometers to Miles" -> value * 0.621371
                    "Kilograms to Pounds" -> value * 2.20462
                    "Meters to Feet" -> value * 3.28084
                    else -> 0.0
                }
                val formattedResult = "%.2f".format(result)
                tvResult.text = "Result: $formattedResult"
            }
        }
    }
}