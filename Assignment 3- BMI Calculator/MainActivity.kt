package com.example.bmicalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightEditText = findViewById<EditText>(R.id.weightEditText)
        val heightEditText = findViewById<EditText>(R.id.heightEditText)
        val calcButton = findViewById<Button>(R.id.calcButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        calcButton.setOnClickListener {

            val weightStr = weightEditText.text.toString()
            val heightStr = heightEditText.text.toString()

            // Validation
            if (weightStr.isEmpty() || heightStr.isEmpty()) {
                Toast.makeText(this, "Please enter weight and height", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weight = weightStr.toDouble()
            val heightCm = heightStr.toDouble()

            // Convert cm → meter
            val heightM = heightCm / 100

            // BMI Formula
            val bmi = weight / heightM.pow(2)

            // Category
            val category = when {
                bmi < 18.5 -> "Underweight"
                bmi < 24.9 -> "Normal"
                bmi < 29.9 -> "Overweight"
                else -> "Obese"
            }

            resultTextView.text = "BMI: %.2f\nCategory: %s".format(bmi, category)
        }
    }
}
