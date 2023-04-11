package com.example.foodscanner.network

data class ProductInfo(
    var ingredients: String = "",
    var productName: String = "",
    var imageUrl: String = "",
    var error: String = ""
)
