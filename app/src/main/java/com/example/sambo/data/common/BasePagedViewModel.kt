package com.example.sambo.data.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

//class чисто для работы с пагинацией
abstract class BasePagedViewModel < T, D : BaseDataSource<T>> : ViewModel() {
               //<T это дата класс (generik)  D : BaseDataSource<T>> это созданный DataSource
                // тип D по умолчан стоит , можно использовать при фрагментах итд!

    //делаем абстрактн экземпляр класса DAtaSource
    abstract val sourceFactory : BaseDataSource.Factory<T, D>

    fun getPagedList(): LiveData<PagedList<T>>{
        return  LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder()  // тут по умолч указываетс все настройки (pageSize , loadedSize)
                .setPageSize(20)
                .build()
        ) .build()
    }
}