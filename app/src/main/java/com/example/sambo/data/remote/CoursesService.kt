package com.example.sambo.data.remote




import com.example.sambo.data.modelcourses.MainCourseModel
import com.example.sambo.data.modelcourses.Rows
import retrofit2.http.GET
import retrofit2.http.Query

interface CoursesService {

    @GET("api/v1/educational_materials")
   suspend fun getCourses(
        @Query("limit") limit: Int,  // для пагинации лимит сколько можно загружать
        @Query("page") page: Int ,
        @Query("order") order: String
    ): MainCourseModel<Rows>
}



// //https://api.sambo.beta.trinitydigital.ru/api/v1/ categories ? limit=20&page=1&order={%22rank%22:%22asc%22}
