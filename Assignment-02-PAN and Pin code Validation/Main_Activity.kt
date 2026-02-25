package com.example.panvalidation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var etPan: EditText
    lateinit var etPincode: EditText
    lateinit var btnValidate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPan = findViewById(R.id.etPan)
        etPincode = findViewById(R.id.etPincode)
        btnValidate = findViewById(R.id.btnValidate)

        btnValidate.setOnClickListener {

            val pan = etPan.text.toString().trim()
            val pincode = etPincode.text.toString().trim()

            // 1️⃣ Check Empty Fields
            if (pan.isEmpty() || pincode.isEmpty()) {
                Toast.makeText(this, "Both fields should not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2️⃣ PAN Validation (10 alphanumeric characters)
            val panRegex = Regex("^[A-Za-z0-9]{10}$")
            if (!panRegex.matches(pan)) {
                Toast.makeText(this, "PAN must be 10 alphanumeric characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3️⃣ Pincode Validation (6 digits only)
            val pinRegex = Regex("^[0-9]{6}$")
            if (!pinRegex.matches(pincode)) {
                Toast.makeText(this, "Pincode must be 6 digits only", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // If all validations passed
            Toast.makeText(this, "Validation Successful ✅", Toast.LENGTH_LONG).show()
        }
    }
}