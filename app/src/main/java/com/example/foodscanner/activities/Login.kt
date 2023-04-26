package com.example.foodscanner.activities

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.foodscanner.databinding.ActivityLoginBinding


class Login: AppCompatActivity()
{
    private lateinit var binding:ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener{
            val eMail = binding.emailBar.text.toString()
            val pwrd = binding.pWordBar.text.toString()

            if(eMail.isNotEmpty() && pwrd.isNotEmpty())
            {
                firebaseAuth.signInWithEmailAndPassword(eMail,pwrd).addOnCompleteListener(){
                    if(it.isSuccessful){
                        val intent = Intent(this, Scan::class.java)
                        startActivity(intent)
                    }
                    else{

                    }
                }
            }
            else
            {

            }

        }

        binding.signUpButton.setOnClickListener{
            val Intent = Intent(this, signUpPage::class.java)
            startActivity(Intent)
        }
    }
    //override fun onStart() {
       // super.onStart()

        //if(firebaseAuth.currentUser != null){
      //      val intent = Intent(this, Scan::class.java)
          //  startActivity(intent)
        //}
    //}
}