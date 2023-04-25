package com.example.foodscanner.activities

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.foodscanner.R
import android.widget.Toast

class Login: AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val logInButton = findViewById<Button>(R.id.btn)
        val userName = findViewById<EditText>(R.id.emailBar)
        val passWrd = findViewById<EditText>(R.id.pWordBar)
        logInButton.setOnClickListener{
            if(userName.editableText.isNullOrBlank()&&passWrd.editableText.isNullOrBlank()) {
                Toast.makeText(this,"Please fill the required fields", Toast.LENGTH_SHORT).show()
            }
            else{
                val Intent = Intent(this, Scan::class.java)
                startActivity(Intent)
            }
        }
    }
}