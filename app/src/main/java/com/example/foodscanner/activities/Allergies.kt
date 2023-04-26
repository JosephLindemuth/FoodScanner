package com.example.foodscanner.activities


import android.os.Bundle
import android.view.LayoutInflater
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.foodscanner.FoodScanner
import com.example.foodscanner.R
import com.example.foodscanner.ui.main.AllergiesFragment.Companion.newInstance


class allergies : AppCompatActivity() {
    private lateinit var app: FoodScanner
    private val selectedItem = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allergies)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, newInstance())
                .commitNow()
        }
        /*
        var listview: ListView  = findViewById<ListView>(R.id.allergy_container)



        val vi = LayoutInflater.from(this).inflate(R.layout.activity_allergies, null);
        listview.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as Int
            if(app.myAllergies.contains(position)){
                app.myAllergies.remove(position)

                vi.setBackgroundColor(resources.getColor(R.color.white))
            }
            else{
                app.myAllergies.add(position)
                vi.setBackgroundColor(resources.getColor(R.color.teal_700))
            }
        })
         */
    }


}