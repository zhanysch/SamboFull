package com.example.sambo.ui.bottomnavigation.courses

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sambo.data.common.BaseDataSource
import com.example.sambo.data.common.BasePagedViewModel
import com.example.sambo.data.model.listing.BaseListingModel
import com.example.sambo.data.model.listing.RowsModel
import com.example.sambo.data.repository.SamboRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoursesViewModel(private val service: SamboRepository) :
    BasePagedViewModel<RowsModel, CoursesViewModel.CourseDataSource>() {


    override val sourceFactory by lazy {
        BaseDataSource.Factory {
            CourseDataSource(viewModelScope)
        }
    }

    val text = MutableLiveData<RowsModel>()
    val data = getPagedList()
    val dataCategory = MutableLiveData<List<RowsModel>>()
    var categoryId: Int =
        -1

    fun loadList() {
        viewModelScope.launch {
            val result = service.loadCategory(limit = 20, page = 1)
            if (result.isSuccessful) dataCategory.postValue(result.body()?.rows)
            Log.d("fsdfsfds", "Fsfsdfsdf")
        }
    }

    fun choosedCategory(item: RowsModel) {
        categoryId = item.id
        sourceFactory.dataSourceFactoryLiveData.value?.invalidate()
    }

    inner class CourseDataSource(
        scope: CoroutineScope
    ) : BaseDataSource<RowsModel>(scope) {

        override fun getListByPageNumber(limit: Int, page: Int):BaseListingModel<RowsModel>? {
            return runBlocking {
                val data = service.loadData(limit = limit, page = page, categoryId = categoryId)

                return@runBlocking data
            }
        }
    }
}


