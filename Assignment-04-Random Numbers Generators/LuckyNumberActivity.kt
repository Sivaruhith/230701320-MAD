package com.example.randomrangeapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class LuckyNumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lucky_number)

        val minEdit = findViewById<EditText>(R.id.minValue)
        val maxEdit = findViewById<EditText>(R.id.maxValue)
        val generateBtn = findViewById<Button>(R.id.generateBtn)
        val resultText = findViewById<TextView>(R.id.resultText)

        generateBtn.setOnClickListener {

            val minStr = minEdit.text.toString()
            val maxStr = maxEdit.text.toString()

            if (minStr.isEmpty() || maxStr.isEmpty()) {
                Toast.makeText(this, "Enter both values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val min = minStr.toInt()
            val max = maxStr.toInt()

            if (min >= max) {
                Toast.makeText(this, "Min must be less than Max", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val randomNumber = Random.nextInt(min, max + 1)

            resultText.text = "🎉 Lucky Number: $randomNumber"
        }
    }
}
