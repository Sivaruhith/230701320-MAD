package com.example.collegevalidation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnValidate = findViewById<Button>(R.id.btnValidate)

        btnValidate.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // 1️⃣ Empty field check
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Both fields should not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2️⃣ College Email Validation
            // Example: must end with @rec.edu.in (change to your college domain)
            val emailRegex = Regex("^[A-Za-z0-9._%+-]+@rec\\.edu\\.in$")
            if (!emailRegex.matches(email)) {
                Toast.makeText(this, "Enter valid college email ID", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

          
            val passwordRegex =
                Regex("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#\$%^&+=!]).{12,}$")

            if (!passwordRegex.matches(password)) {
                Toast.makeText(
                    this,
                    "Password must contain 1 uppercase, 1 number, 1 special symbol & min 12 chars",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            // If all validations passed
            Toast.makeText(this, "Validation Successful ✅", Toast.LENGTH_LONG).show()
        }
    }
}