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
val Milk = listOf("Butter", "butter fat", "butter oil", "butter acid", "butter ester(s)", "Buttermilk",
    "Casein", "Casein hydrolysate", "Caseinates", "Cheese", "Cottage cheese", "Cream", "Curds", "Custard",
    "Ghee", "Half-and-half", "Lactalbumin", "lactalbumin phosphate", "Lactic acid starter culture", "Lactoferrin",
    "Lactoglobulin", "Lactose", "Lactulose", "Milk", "condensed milk", "Milk protein hydrolysate", "Pudding",
    "Recaldent", "Rennet casein", "Simplesse", "Sour cream", "sour cream solids", "Sour milk solids", "Tagatose",
    "Whey", "Whey protein hydrolysate", "Yogurt")

val Eggs = listOf("Albumin","Albumen", "Apovitellin", "Avidin globulin", "Egg", "Eggnog", "Lysozyme", "Mayonnaise",
    "Meringue", "Ovalbumin", "Ovomucoid", "Ovomucin", "Ovovitellin", "Surimi", "Vitellin")

val Soy = listOf("Cold-pressed soy oil", "expelled soy oil", "extruded soy oil", "Edamame", "Miso", "Natto", "Okara",
    "Shoyu", "Soy", "soy albumin", "soy cheese", "soy fiber", "soy flour", "soy grits", "soy ice cream", "soy milk",
    "soy nuts", "soy sprouts", "soy yogurt", "Soya", "Soybean curd", "Soybean granules", "Soy protein", "Soy sauce",
    "Tamari", "Tempeh", "Textured vegetable protein (TVP)", "TVP", "Tofu")

val Fish = listOf("Anchovies","Bass","Catfish","Cod","Flounder","Grouper","Haddock","Hake","Halibut",
    "Herring","Mahi mahi","Perch","Pike","Pollock","Salmon","Scrod","Sole","Snapper","Swordfish",
    "Tilapia","Trout","Tuna","Fish Flavoring","Fish gelatin","Fish oil","Fish sticks",
    "Imitation Fish","Imitation Crab","Artificial fish","shellfish","surimi","sea legs","sea sticks")

val Shellfish = listOf("Barnacle", "Crab", "Crawfish", "crawdad", "crayfish", "ecrevisse", "Krill", "Lobster",
    "langouste", "langoustine", "Moreton bay bugs", "scampi", "tomalley", "Prawns", "Shrimp", "crevette", "scampi",
    "Abalone", "Clams", "Cockle", "Cuttlefish", "Limpet", "lapas", "opihi", "Mussels", "Octopus", "Oysters",
    "Periwinkle", "Sea cucumber", "Sea urchin", "Scallops", "Snails", "escargot", "Squid", "calamari", "Whelk",
    "Turban shell", "Bouillabaisse", "Cuttlefish ink", "Glucosamine", "Fish stock", "Seafood flavoring", "crab flavoring",
    "clam flavoring", "clam extract", "Fish stock", "fish sauce", "krill", "Surimi")

val Tree_Nuts = listOf("Nut", "Almond", "Artificial nuts", "Beechnut", "Black walnut hull extract", "Black walnut hull  flavoring", "Brazil nut",
    "Butternut", "white walnuts", "Cashew", "Chestnut", "Chinquapin nut", "Coconut", "Filbert", "hazelnut", "Gianduja",
    "Ginkgo nut", "Hickory nut", "Litchi", "lichee", "lychee nut", "Macadamia nut", "Marzipan paste", "almond paste",
    "Nangai nut", "Natural nut extract", "almond extract", "walnutnut extract", "Nut butter", "cashew butter", "Nut distillates",
    "Nut alcoholic extracts", "Nut meal", "Nut meat", "Nut milk", "almond milk", "cashew milk", "Nut oils", "walnut oil",
    "almond oil", "Nut paste", "almond paste", "Nut pieces", "Pecan", "Pesto", "Pili nut", "Pine nut", "Indian nut", "pignoli nut",
    "pigñolia  nut", "pignon nut", "piñon nut", "pinyon nut", "Pistachio", "Praline", "Shea nut", "Walnut", "Walnut hull extract", "Wallnut hull flavoring")

val Peanuts = listOf("Peanut", "Arachis oil", "peanut oil", "Artificial nuts", "Beer nuts", "Cold-pressed peanut oil", "expelled peanut oil",
    "extruded peanut oil", "Ground nuts", "Lupin", "lupine", "Mandelonas", "Mixed nuts", "Monkey nuts", "Nut meat", "nut meal",
    "Nut pieces", "Peanut butter", "Peanut flour", "Peanut protein hydrolysate")

val Wheat = listOf("Bread crumbs", "Bulgur", "Cereal extract", "Club wheat", "Couscous", "Cracker meal", "Durum", "Einkorn",
    "Emmer", "Farina", "Farro", "Flour", "Freekeh", "Hydrolyzed wheat protein", "Kamut", "Matzoh", "matzoh meal",
    "matzo", "matzah", "matza", "Pasta", "Seitan", "Semolina", "Spelt", "Sprouted wheat", "Triticale", "Vital wheat gluten",
    "Wheat", "bran", "durum", "germ", "gluten", "grass", "malt", "sprouts", "starch", "Wheat bran hydrolysate",
    "Wheat germ oil", "Wheat grass", "Wheat protein isolate", "Whole wheat berries", "Glucose syrup", "Soy sauce",
    "Starch", "gelatinized starch", "modified starch", "modified food starch", "vegetable starch", "Surimi", "Plant-based meat alternatives")

val Sesame = listOf("Sesame", "Benne", "benne seed", "benniseed", "Gingelly", "gingelly oil", "Gomasio", "sesame salt",
    "Halvah", "Sesame flour", "Sesame oil", "Sesame paste", "Sesame salt", "Sesame seed", "Sesamol",
    "Sesamum indicum", "Sesemolina", "Sim sim", "Tahini", "Tahina", "Tehina", "Til")

val Potato = listOf("Potato"," Potatoes")

val Pork = listOf("Pork")

val Pea = listOf("Pea", "peas")

public val ingredientsOfDoom : List<List<String>> = listOf(
    Milk,
    Eggs,
    Soy,
    Fish,
    Shellfish,
    Tree_Nuts,
    Peanuts,
    Wheat,
    Sesame,
    Potato,
    Pork,
    Pea)

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
                for(allergy: String in Fish) {
                    if (item.contains(allergy,true)) {
                        parsed.allergens = parsed.allergens + ", ${item}"

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
