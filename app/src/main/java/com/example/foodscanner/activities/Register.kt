package com.example.foodscanner.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.content.Intent
import com.example.foodscanner.R

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var editUsername = findViewById(R.id.editUsername) as EditText
        var editEmail = findViewById(R.id.editEmail) as EditText
        var editPassword = findViewById(R.id.editPassword) as EditText
        var editCPassword = findViewById(R.id.editCPassword) as EditText
        val btnLogin = findViewById(R.id.tvLogin) as Button
        val btnRegister = findViewById(R.id.btnRegister) as Button

        btnRegister.setOnClickListener {
            if (editUsername.text.trim().isNotEmpty() || editEmail.text.trim().isNotEmpty()||
                editPassword.text.trim().isNotEmpty()|| editCPassword.text.trim().isNotEmpty()) {
                Toast.makeText(this, "Input provided", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()
            }
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java);
            startActivity(intent);
        }

    }
}