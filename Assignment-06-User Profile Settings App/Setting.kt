// MainActivity.kt
package com.example.userprofilesettings

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAge: EditText
    private lateinit var etBio: EditText
    private lateinit var radioGroupColor: RadioGroup
    private lateinit var radioRed: RadioButton
    private lateinit var radioBlue: RadioButton
    private lateinit var radioGreen: RadioButton
    private lateinit var btnSave: Button

    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val PREF_NAME = "UserProfilePrefs"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etAge = findViewById(R.id.etAge)
        etBio = findViewById(R.id.etBio)

        radioGroupColor = findViewById(R.id.radioGroupColor)
        radioRed = findViewById(R.id.radioRed)
        radioBlue = findViewById(R.id.radioBlue)
        radioGreen = findViewById(R.id.radioGreen)

        btnSave = findViewById(R.id.btnSave)

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        // Load saved data automatically
        loadData()

        btnSave.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val age = etAge.text.toString()
        val bio = etBio.text.toString()

        val selectedId = radioGroupColor.checkedRadioButtonId
        val selectedRadio = findViewById<RadioButton>(selectedId)

        var favouriteColor = ""
        if (selectedRadio != null) {
            favouriteColor = selectedRadio.text.toString()
        }

        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("email", email)
        editor.putString("age", age)
        editor.putString("bio", bio)
        editor.putString("colour", favouriteColor)
        editor.apply()

        Toast.makeText(this, "Profile Saved Successfully", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        etName.setText(sharedPreferences.getString("name", ""))
        etEmail.setText(sharedPreferences.getString("email", ""))
        etAge.setText(sharedPreferences.getString("age", ""))
        etBio.setText(sharedPreferences.getString("bio", ""))

        val savedColor = sharedPreferences.getString("colour", "")

        when (savedColor) {
            "Red" -> radioRed.isChecked = true
            "Blue" -> radioBlue.isChecked = true
            "Green" -> radioGreen.isChecked = true
        }
    }
}