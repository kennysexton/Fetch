package com.kennysexton.fetch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennysexton.fetch.modal.FetchData
import com.kennysexton.fetch.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FetchViewModel : ViewModel() {

    private val _dataList = MutableStateFlow<List<FetchData>>(emptyList())
    val dataList: StateFlow<List<FetchData>> = _dataList.asStateFlow()

    init {
        getData()
    }

    /**
     * Get data in a coroutine to not block the main thread
     * Using the IO thread for network calls
     */
    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.apiService.getFetchData()

                if (response.isSuccessful) {
                    response.body()?.let {
                        _dataList.value = sortData(filterData(it))
                    }
                } else {
                    Log.e("FetchViewModel", "Could not get data from API")
                }


            } catch (e: Exception) {
                Log.e("FetchViewModel", "Error fetching data ${e.message}")
            }
        }
    }

    /**
     * Remove nulls and blank values
     */
    private fun filterData(data: List<FetchData>): List<FetchData> {
        return data.filter { it.name?.isNotBlank() == true }
    }

    /**
     * Sort the data by listId and then by id
     */
    private fun sortData(data: List<FetchData>): List<FetchData> {
        return data.sortedWith(compareBy<FetchData> { it.listId }.thenBy { it.id })
    }

}
