package com.example.foodscanner.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.foodscanner.FoodScanner
import com.example.foodscanner.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URL

class ScanSuccessful : AppCompatActivity() {

//    private val client = OkHttpClient()
//
//    fun run() {
//        val request = Request.Builder()
//            .url("https://publicobject.com/helloworld.txt")
//            .build()
//
//        Log.d("GET request test", "Request made")
//
//        client.newCall(request).execute().use { response ->
//            Log.d("GET request test", "responding")
//            if (!response.isSuccessful) throw IOException("Unexpected code $response")
//
//            Log.d("GET request test", "Response was successful")
//            for ((name, value) in response.headers) {
//                println("$name: $value")
//            }
//
//            println(response.body!!.string())
//        }
//    }

    // Create OkHttp Client
//    var client: OkHttpClient = OkHttpClient();
//    private fun getRequest(sUrl: String): String? {
//        var result: String? = null
//        try {
//            // Create URL
//            val url = URL(sUrl)
//            // Build request
//            val request = Request.Builder().url(url).build()
//            // Execute request
//            val response = client.newCall(request).execute()
//            result = response.body?.string()
//        }
//        catch(err:Error) {
//            print("Error when executing get request: "+err.localizedMessage)
//        }
//        return result
//    }
//
//    data class BlogInfo(val title: String, val description: String)
//
//    private fun fetch(sUrl: String): String {
//        var blogInfo: BlogInfo? = null
//        var message: String = ""
//        lifecycleScope.launch(Dispatchers.IO) {
//            val result = getRequest(sUrl)
//            if (result != null) {
//                try {
//                    // Parse result string JSON to data class
////                    blogInfo = Klaxon().parse<bloginfo>(result)
//                }
//                catch(err:Error) {
//                    print("Error when parsing JSON: "+err.localizedMessage)
//                    message = "Parse failure"
//                }
//            }
//            else {
//                print("Error: Get request returned no response")
//                message = "GET failure"
//            }
//        }
//        return message
//    }

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_successful)

        val scanHistory: Array<String> = (application as FoodScanner).scanHistory.toTypedArray();

//        scanHistory.forEachIndexed { index, barcode ->
//            Log.d("History Value $index", barcode)
//        }

//        CronetProviderInstaller.installProvider(Context)
//
//        val myBuilder = CronetEngine.Builder(context)
//        val cronetEngine: CronetEngine = myBuilder.build()

//        AndroidNetworking.initialize(getApplicationContext());

//        run();
//        Log.d("GET request test", "Look above ^^^")

//        val message = fetch("https://raw.githubusercontent.com/justmobiledev/android-kotlin-rest-1/main/support/data/bloginfo.json")
//        Log.d("GET request test", message)

        val textView = findViewById<View>(R.id.scannedItem) as TextView
        textView.text = scanHistory.last();

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigator)
        bottomNavigationView.selectedItemId
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Scan -> {
                    val outIntent: Intent = Intent(applicationContext, Scan::class.java)
                    startActivity(outIntent)

                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fav -> {
                    val outIntent: Intent = Intent(applicationContext, Favorites::class.java)
                    startActivity(outIntent)

                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.History -> return@OnNavigationItemSelectedListener true
            }
            false
        })
    }
}