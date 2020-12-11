package com.example.sambo.data.repository

import com.example.sambo.data.modelcourses.MainCourseModel
import com.example.sambo.data.modelcourses.Rows
import com.example.sambo.data.interactors.SamboInteractor

interface SamboRepository {
    suspend fun loadData(limit: Int, page: Int) : MainCourseModel<Rows>
}
class SamboRepositoryImpl(private val network : SamboInteractor) : SamboRepository {

    override suspend fun loadData(limit: Int, page: Int): MainCourseModel<Rows> {
        return network.loadData(limit=limit,page = page)
    }
}