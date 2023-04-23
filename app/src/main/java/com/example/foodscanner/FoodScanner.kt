package com.example.foodscanner

import android.R
import android.app.Application
import android.view.Menu
import android.view.MenuInflater


class FoodScanner : Application() {
    val fish = listOf("Anchovies","Bass","Catfish","Cod","Flounder","Grouper","Haddock","Hake","Halibut",
    "Herring","Mahi mahi","Perch","Pike","Pollock","Salmon","Scrod","Sole","Snapper","Swordfish",
    "Tilapia","Trout","Tuna","Fish Flavoring","Fish gelatin","Fish oil","Fish sticks",
    "Imitation Fish","Imitation Crab","Artificial fish","shellfish","surimi","sea legs","sea sticks")
    public val ingredientsOfDoom : List<List<String>> = listOf(fish)
    public val favScans = HashSet<String>()
    public val scanHistory = HashSet<String>() // Hashset is unordered which should be change to be an ordered list
    public var lastScanned: String = ""

    override fun onCreate() {
        super.onCreate()
    }

}