package com.example.sambo.ui.bottomnavigation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sambo.data.model.advice.AdviceOfDayModel
import com.example.sambo.data.model.listing.BaseListingModel
import com.example.sambo.data.model.listing.RowsModel
import com.example.sambo.data.repository.SamboRepository
import com.example.sambo.utils.ext.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SamboRepository) : ViewModel() {

    val cardsData = MutableLiveData<BaseListingModel<RowsModel>>()
    val collectionsData = MutableLiveData<BaseListingModel<RowsModel>>()
    val newsData = SingleLiveEvent<BaseListingModel<RowsModel>>()
    val adviceData = SingleLiveEvent<AdviceOfDayModel>()

    fun loadCards() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val result = repository.loadCards(20, 1)
                cardsData.postValue(result?.body())
            }.onFailure {

            }
        }
    }

    fun loadCollections() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val result = repository.loadCollections(20, 1)
                collectionsData.postValue(result?.body())
            }.onFailure {

            }
        }
    }

    fun loadNews() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val result = repository.loadNews(20, 1)
                newsData.postValue(result?.body())
            }.onFailure {

            }
        }
    }

    fun adviceOfDay() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val result = repository.adviceOfDay()
                adviceData.postValue(result)
            }.onFailure {

            }
        }
    }
}

