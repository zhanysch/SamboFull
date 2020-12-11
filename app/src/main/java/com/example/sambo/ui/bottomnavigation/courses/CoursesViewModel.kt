package com.example.sambo.ui.bottomnavigation.courses

import com.example.sambo.data.modelcourses.MainCourseModel
import com.example.sambo.data.modelcourses.Rows
import androidx.lifecycle.viewModelScope
import com.example.sambo.data.commonpagination.BaseDataSource
import com.example.sambo.data.commonpagination.BasePagedViewModel
import com.example.sambo.data.repository.SamboRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

class CoursesViewModel(private val service : SamboRepository) : BasePagedViewModel<Rows, CoursesViewModel.CourseDataSource>(){

    override val sourceFactory by lazy {
        BaseDataSource.Factory{
            CourseDataSource(viewModelScope)
        }
    }

    val data = getPagedList()

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