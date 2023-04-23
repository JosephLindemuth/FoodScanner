package com.example.foodscanner.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodscanner.FoodScanner
import com.example.foodscanner.R
import com.example.foodscanner.ui.FavoritesRecyclerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class Favorites : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val favScans: Array<String> = (application as FoodScanner).favScans.toTypedArray();

        favScans.forEachIndexed { index, barcode ->
            Log.d("Favorite Value $index", barcode)
        }

        // set up RecyclerView for files

        val favScanList: RecyclerView = findViewById<RecyclerView>(R.id.FavScans)
        favScanList.layoutManager = LinearLayoutManager(this)
        favScanList.setHasFixedSize(true)
        val favoritesAdapter = FavoritesRecyclerAdapter(favScans)
        favScanList.adapter = favoritesAdapter

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigator)
        bottomNavigationView.selectedItemId
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.History -> {
                    val outIntent: Intent = Intent(applicationContext, History::class.java)
                    startActivity(outIntent)
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Scan -> {
                    val outIntent: Intent = Intent(applicationContext, Scan::class.java)
                    startActivity(outIntent)
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fav -> return@OnNavigationItemSelectedListener true
            }
            false
        })

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_topright, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.AllergiesMenu -> {
                val outIntent: Intent = Intent(applicationContext, allergies::class.java)
                startActivity(outIntent)
                overridePendingTransition(0, 0)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    /*
    fun getMyData(): Bundle? {
        val bundle = Bundle()
        bundle.putStringArray("favScans", favScans)
        return bundle
    }
    */

}