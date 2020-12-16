package com.example.sambo.data.interactors

import com.example.sambo.data.modelBottomSheet.BottomSheetModel
import com.example.sambo.data.modelBottomSheet.BottomSheetRows
import com.example.sambo.data.modelcourses.MainCourseModel
import com.example.sambo.data.modelcourses.Rows
import com.example.sambo.data.remote.CoursesService
import retrofit2.Call
import retrofit2.Response

interface SamboInteractor{
    suspend fun loadData(limit: Int, page: Int) : MainCourseModel<Rows>
    suspend fun loadCategory(limit: Int,page: Int)  : Response<BottomSheetModel>

}


class SamboInteractorImpl (private val service : CoursesService) : SamboInteractor{
    override suspend fun loadData(limit: Int, page: Int): MainCourseModel<Rows> {
        return service.getCourses(limit = limit, page = page,order = "{\"id\":\"asc\"}")
    }

    override suspend fun loadCategory(limit: Int, page: Int): Response<BottomSheetModel> {
        return service.getBottomSheet(limit=20,page = 1,order ="{\"id\":\"asc\"}")
    }


}