/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.foodscanner.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodscanner.FoodScanner
import com.example.foodscanner.network.BarcodeApi
import com.example.foodscanner.network.ProductInfo
import kotlinx.coroutines.launch
import org.json.JSONObject

val fish = listOf("Anchovies","Bass","Catfish","Cod","Flounder","Grouper","Haddock","Hake","Halibut",
    "Herring","Mahi mahi","Perch","Pike","Pollock","Salmon","Scrod","Sole","Snapper","Swordfish",
    "Tilapia","Trout","Tuna","Fish Flavoring","Fish gelatin","Fish oil","Fish sticks",
    "Imitation Fish","Imitation Crab","Artificial fish","shellfish","surimi","sea legs","sea sticks")
public val ingredientsOfDoom : List<List<String>> = listOf(fish)

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {
    private lateinit var app: FoodScanner
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>("Loading product information...")

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    init {    } // does nothing

    /**
     * Gets Item info information from the Barcode API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    fun getItemInfo(upc: String) {

        viewModelScope.launch {

            try { // "$BASE_URL$upc.json"
                val apiResponse: String? = BarcodeApi.retrofitService.getInfo("${Companion.BASE_URL}$upc.json")
                Log.d("Results", apiResponse.toString())
                val productInfo = parseResponse(apiResponse)
                _status.value = "Food info received: \n\nName: ${productInfo.productName} \n\nIngredients: ${productInfo.ingredients} \n\nImage URL: ${productInfo.imageUrl} \n\n Allergens: ${productInfo.allergens}"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }


    private fun parseResponse(response: String?): ProductInfo {
        val parsed = ProductInfo()
        val json = JSONObject(response)



        if (json.getInt("status") == 1) {
            val jsonProduct = json.getJSONObject("product")
            parsed.ingredients = jsonProduct.getString("ingredients_text")

            parsed.productName = smartGetProductName(jsonProduct)

            parsed.imageUrl = jsonProduct.getString("image_front_small_url")

            parsed.allergens = jsonProduct.getString("allergens_from_ingredients")

            for(item: String in parsed.ingredients.split(",")){
                Log.d("Ingredient: ",item)
                for(allergy: String in fish) {
                    if (item.contains(allergy,true)) {
                        parsed.allergens = parsed.allergens + ", ${allergy}"

                    }
                }
            }
        } else {
            parsed.error = "Product not found"
        }

        return parsed
    }

    // js code that does what the below function needs to:
//    function smartGetProductName(product) {
//        for(key of Object.keys(product)) {
//            if (key.startsWith("product_name")) {
//                return product[key];
//            }
//        }
//    }

    // Finds first field that starts with "product_name" and returns associated value NOT IMPLEMENTED
    private fun smartGetProductName(product: JSONObject): String {
        return product.getString("product_name")
    }

    // Holds BASE_URL constant
    companion object {
        private const val BASE_URL = "https://world.openfoodfacts.org/api/v0/product/"
    }
}
