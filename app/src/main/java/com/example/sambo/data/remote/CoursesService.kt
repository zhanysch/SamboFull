package com.example.sambo.data.remote




import com.example.sambo.data.modelBottomSheet.BottomSheetModel
import com.example.sambo.data.modelBottomSheet.BottomSheetRows
import com.example.sambo.data.modelcourses.MainCourseModel
import com.example.sambo.data.modelcourses.Rows
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoursesService {

    @GET("api/v1/educational_materials")
   suspend fun getCourses(
        @Query("limit") limit: Int,  // для пагинации лимит сколько можно загружать
        @Query("page") page: Int ,
        @Query("order") order: String
    ): MainCourseModel<Rows>

    //https://api.sambo.beta.trinitydigital.ru/ api/v1/educational_materials ? limit=20 & page=1 & category_id=1 & order={"id":"asc"}

   @GET("api/v1/categories")
   suspend fun getBottomSheet(
       @Query("limit") limit: Int,  // для пагинации лимит сколько можно загружать
       @Query("page") page: Int ,
       @Query("order") order: String
   ) : Response<BottomSheetModel>

   // https://api.sambo.beta.trinitydigital.ru/ api/v1/categories ? limit =20 & page=1 & order="{\"id\":\"asc\"}"

    @GET("api/v1/educational_materials")
    suspend fun getCategoryId(
        @Query("limit") limit: Int,  // для пагинации лимит сколько можно загружать
        @Query("page") page: Int ,
        @Query("order") order: String,
        @Query("category_id") categoryId: Int
    ): MainCourseModel<Rows>
}


