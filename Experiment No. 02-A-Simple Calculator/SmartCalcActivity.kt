package com.example.smartcalc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SmartCalcActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var current = ""
    private var operator = ""
    private var firstNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_calc)

        display = findViewById(R.id.display)

        val numberButtons = listOf(
            R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,
            R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9
        )

        numberButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                val value = (it as Button).text.toString()
                appendNumber(value)
            }
        }

        findViewById<Button>(R.id.btnDot).setOnClickListener { appendNumber(".") }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { setOperator("/") }

        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculate() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.btnBack).setOnClickListener { backspace() }
    }

    private fun appendNumber(num: String) {
        if (num == "." && current.contains(".")) return
        current += num
        display.text = current
    }

    private fun setOperator(op: String) {
        if (current.isEmpty()) return
        firstNumber = current.toDouble()
        operator = op
        current = ""
    }

    private fun calculate() {
        if (current.isEmpty()) return
        val secondNumber = current.toDouble()
        val result = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else {
                display.text = "Error"
                current = ""
                return
            }
            else -> return
        }

        display.text = result.toString()
        current = result.toString()
    }

    private fun clearAll() {
        current = ""
        operator = ""
        firstNumber = 0.0
        display.text = "0"
    }

    private fun backspace() {
        if (current.isNotEmpty()) {
            current = current.dropLast(1)
            display.text = if (current.isEmpty()) "0" else current
        }
    }
}
