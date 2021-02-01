package com.example.sambo.ui.collectionsdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sambo.data.model.listing.BaseListingModel
import com.example.sambo.data.model.listing.RowsModel
import com.example.sambo.data.repository.SamboRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CollectionsDetailsViewModel(private val repository: SamboRepository) : ViewModel() {

    val collectionsData = MutableLiveData<BaseListingModel<RowsModel>>()

    fun loadSelectionsData(categoryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val result = repository.loadSelectionsData(20, categoryId, 1)
                collectionsData.postValue(result.body())
            }.onFailure {

            }
        }
    }
}