package com.example.foodscanner.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.foodscanner.R
import android.content.Intent
import com.example.foodscanner.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth


class signUpPage: AppCompatActivity()
{
    private lateinit var binding:ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_signup)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.backBtn.setOnClickListener(){
            val Intent = Intent(this, Login::class.java)
            startActivity(Intent)
        }

        binding.signMeUpBtn.setOnClickListener{

            val eMail = binding.signUpEmail.text.toString()
            val pwrd = binding.createPassword.text.toString()
            val cPwrd = binding.confirmPassword.text.toString()

            if(eMail.isNotEmpty() && pwrd.isNotEmpty() && cPwrd.isNotEmpty())
            {
                if(pwrd == cPwrd)
                {
                        firebaseAuth.createUserWithEmailAndPassword(eMail, pwrd).addOnCompleteListener(){
                            if(it.isSuccessful)
                            {
                                val Intent = Intent(this, Login::class.java)
                                startActivity(Intent)
                            }
                            else
                            {

                            }
                        }
                }
                else
                {

                }
            }
            else
            {

            }
        }



    }

}