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
import com.example.foodscanner.network.BarcodeApi
import kotlinx.coroutines.launch
import com.fasterxml.jackson.module.kotlin.*
import java.net.URL

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    private val mapper = jacksonObjectMapper()

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
    /**
     * Call getItemInfo() on init so we can display status immediately.
     */
    init {
        getItemInfo()
    }

    /**
     * Gets Item info information from the Barcode API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getItemInfo() {
        viewModelScope.launch {
            try {
                val listResult: String = BarcodeApi.retrofitService.getInfo()
                Log.d("Results", listResult)
                val obj1 = mapper.readValue<String>(listResult)
                val obj2 = mapper.readValue<URL>("https://world.openfoodfacts.org/api/v0/product/070847811169.json/")
                _status.value = "Success: $listResult food info received"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}
