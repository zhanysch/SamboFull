package com.example.sambo.ui.bottomnavigation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sambo.data.model.cards.CardsModel
import com.example.sambo.data.repository.SamboRepository
import com.example.sambo.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CollectionsDetailsViewModel (private val repository: SamboRepository)  : ViewModel() {

    val collectionsData = SingleLiveEvent<CardsModel>()

    fun loadSelectionsData(categoryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val result = repository.loadSelectionsData(20,categoryId,1)
                collectionsData.postValue(result.body())
            }.onFailure {

            }
        }
    }
}