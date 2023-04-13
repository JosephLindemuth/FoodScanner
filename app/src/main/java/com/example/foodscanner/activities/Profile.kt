package com.example.foodscanner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.content.Intent
import com.example.foodscanner.R

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var edUsername = findViewById(R.id.edUsername) as EditText
        var edPassword = findViewById(R.id.edPassword) as EditText
        val btnLogin = findViewById(R.id.btnLogin) as Button
        val btnRegister = findViewById(R.id.tvRegister) as Button

        btnLogin.setOnClickListener {
            if(edUsername.text.trim().isNotEmpty() || edPassword.text.trim().isNotEmpty()) {
                Toast.makeText(this, "Input provided", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()
            }
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java);
            startActivity(intent)
        }
    }
}