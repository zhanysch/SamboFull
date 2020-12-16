package com.example.sambo.ui.bottomnavigation.courses

import android.app.Dialog
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sambo.data.modelcourses.MainCourseModel
import com.example.sambo.data.modelcourses.Rows
import androidx.lifecycle.viewModelScope
import com.example.sambo.data.commonpagination.BaseDataSource
import com.example.sambo.data.commonpagination.BasePagedViewModel
import com.example.sambo.data.modelBottomSheet.BottomSheetRows
import com.example.sambo.data.repository.SamboRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Response

class CoursesViewModel(private val service : SamboRepository) : BasePagedViewModel<Rows, CoursesViewModel.CourseDataSource>(){



    override val sourceFactory by lazy {
        BaseDataSource.Factory{
            CourseDataSource(viewModelScope)
        }
    }

    val data = getPagedList()
    val dataCategory = MutableLiveData<List<BottomSheetRows>>()


    fun loadList(){
        viewModelScope.launch {
            val result = service.loadCategory(limit = 20, page = 1)
            if (result.isSuccessful) dataCategory.postValue(result.body()?.rows)
            Log.d("fsdfsfds","Fsfsdfsdf")
        }
    }

    inner class CourseDataSource(  // это внутренний класс, а не метод
        scope : CoroutineScope
    ) : BaseDataSource<Rows>(scope){//<Data> это class  тут укзываетс, чтo тип T это -> class Data (data class)
        override fun getListByPageNumber(limit: Int, page: Int): MainCourseModel<Rows>? {
            return runBlocking {
                val data = service.loadData(limit=limit,page = page)
                return@runBlocking data
            }
        }

    }


}


