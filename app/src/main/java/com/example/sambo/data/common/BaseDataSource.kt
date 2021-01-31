package com.example.sambo.data.common

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.sambo.data.model.listing.BaseListingModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// class чисто для работы с пагинацией  тоесть управляет списком пагинации!!!!!!!
abstract class BaseDataSource<T>(
    private val scope: CoroutineScope  // скачивает данные пагинации
) : PageKeyedDataSource<Int, T>() {
    //PageKeyedDataSource  это страница <Int, T>() стрнц запускает Int , хранит данные типом Т

    abstract fun getListByPageNumber(limit: Int, page: Int): BaseListingModel<T>?

    private val offset = 20
    private val limit = 20

    class Factory<T, D : BaseDataSource<T>>(private val factory: () -> D) :
        DataSource.Factory<Int, T>() {

        val dataSourceFactoryLiveData =
            MutableLiveData<BaseDataSource<T>>()  // функция возвращает список из дата класс

        override fun create() = factory().apply { dataSourceFactoryLiveData.postValue(this) }
    }

    override fun loadInitial(  // загружает данные
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        scope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val result = getListByPageNumber(
                    limit = limit,
                    page = 0
                )  // на превой загрузке загрузка = 0 & limit скачивает 20 итемов
                result?.rows?.let { callback.onResult(it, 0, 1) }
            }.onFailure {
                Log.d("dgsgsdg", "fssfsf")
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, T>
    ) { // загружает дальше данные, после того как лимит прошли
        scope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val result = getListByPageNumber(
                    page = 20 * params.key,
                    limit = limit
                )  // скачиваем след список
                result?.rows?.let { callback.onResult(it, params.key + 1) }
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {

    }

}
