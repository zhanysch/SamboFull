package com.example.sambo.data.repository

import com.example.sambo.data.modelcourses.MainCourseModel
import com.example.sambo.data.modelcourses.Rows
import com.example.sambo.data.interactors.SamboInteractor
import com.example.sambo.data.modelBottomSheet.BottomSheetModel
import com.example.sambo.data.modelBottomSheet.BottomSheetRows
import retrofit2.Call
import retrofit2.Response

interface SamboRepository {
    suspend fun loadData(limit: Int, page: Int) : MainCourseModel<Rows>
    suspend fun loadCategory(limit: Int,page: Int)  : Response<BottomSheetModel>
}
class SamboRepositoryImpl(private val network : SamboInteractor) : SamboRepository {

    override suspend fun loadData(limit: Int, page: Int): MainCourseModel<Rows> {
        return network.loadData(limit=limit,page = page)
    }

    override suspend fun loadCategory(limit: Int, page: Int): Response<BottomSheetModel>{
        return network.loadCategory(limit,page)
    }


}