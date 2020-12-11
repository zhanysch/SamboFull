package com.example.sambo.data.interactors

import com.example.sambo.data.modelcourses.MainCourseModel
import com.example.sambo.data.modelcourses.Rows
import com.example.sambo.data.remote.CoursesService

interface SamboInteractor{
    suspend fun loadData(limit: Int, page: Int) : MainCourseModel<Rows>

}


class SamboInteractorImpl (private val service : CoursesService) : SamboInteractor{
    override suspend fun loadData(limit: Int, page: Int): MainCourseModel<Rows> {
        return service.getCourses(limit = limit, page = page,order = "{\"id\":\"asc\"}")
    }
}