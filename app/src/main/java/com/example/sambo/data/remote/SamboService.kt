package com.example.sambo.data.remote

import com.example.sambo.data.model.advice.AdviceOfDayModel
import com.example.sambo.data.model.cards.CardsModel
import com.example.sambo.data.model.cards.RowsItem
import com.example.sambo.data.model.news.NewsModel
import com.example.sambo.data.modelBottomSheet.BottomSheetModel
import com.example.sambo.data.modelcourses.MainCourseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SamboService {

    @GET("api/v1/educational_materials")
   suspend fun getCourses(
        @Query("limit") limit: Int,  // для пагинации лимит сколько можно загружать
        @Query("page") page: Int ,
        @Query("order") order: String
    ): MainCourseModel<RowsItem>

    //https://api.sambo.beta.trinitydigital.ru/ api/v1/educational_materials ? limit=20 & page=1 & category_id=1 & order={"id":"asc"}

   @GET("api/v1/categories")
   suspend fun getBottomSheet(
       @Query("limit") limit: Int,  // для пагинации лимит сколько можно загружать
       @Query("page") page: Int ,
       @Query("order") order: String
   ) : Response<BottomSheetModel>

   // https://api.sambo.beta.trinitydigital.ru/ api/v1/categories ? limit =20 & page=1 & order="{\"id\":\"asc\"}"

    @GET("api/v1/educational_materials")   // api для подргузк данных при клике категории
    suspend fun getCategoryId(
        @Query("limit") limit: Int,  // для пагинации лимит сколько можно загружать
        @Query("page") page: Int ,
        @Query("order") order: String,
        @Query("category_id") categoryId: Int   // "category_id") categoryId: Int   добавил дополнит, скопиров getCourses для подгрузк данных при клике
    ): MainCourseModel<RowsItem>

    @GET("api/v1/main_materials")
    suspend fun loadCards(
        @Query("limit") limit: Int,
        @Query("page") page: Int ,
        @Query("order") order: String
    ): Response<CardsModel>

//    https://api.sambo.beta.trinitydigital.ru/api/v1/main_materials?limit=20&page=1&order={"rank":"asc"}

    @GET("api/v1/selections")
    suspend fun loadCollections(
        @Query("limit") limit: Int,
        @Query("page") page: Int ,
        @Query("order") order: String
    ): Response<CardsModel>

    // https://api.sambo.beta.trinitydigital.ru/api/v1/selections?limit=20&page=1&order={"rank":"asc"}

    @GET("api/v1/educational_materials")
    suspend fun loadSelectionsData(
        @Query("limit") limit: Int,
        @Query("selection_id") selectionId: Int ,
        @Query("page") page: Int
    ): Response<CardsModel>

    @GET("api/v1/articles")
    suspend fun loadNews(
        @Query("limit") limit: Int,
        @Query("page") page: Int ,
        @Query("order") order: String
    ): Response<NewsModel>

    // https://api.sambo.beta.trinitydigital.ru/api/v1/articles?limit=20&page=1&order={"created_at":"asc"}

    @GET("api/v1/advices/1")
    suspend fun adviceOfDay(
    ) : AdviceOfDayModel
}


