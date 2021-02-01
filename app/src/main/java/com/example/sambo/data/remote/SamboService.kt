package com.example.sambo.data.remote

import com.example.sambo.data.model.advice.AdviceOfDayModel
import com.example.sambo.data.model.listing.BaseListingModel
import com.example.sambo.data.model.listing.RowsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SamboService {

    @GET("api/v1/educational_materials")
    suspend fun getCourses(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String
    ): BaseListingModel<RowsModel>

    @GET("api/v1/categories")
    suspend fun getBottomSheet(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String
    ): Response<BaseListingModel<RowsModel>>

    @GET("api/v1/educational_materials")
    suspend fun getCategoryId(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String,
        @Query("category_id") categoryId: Int
    ): BaseListingModel<RowsModel>

    @GET("api/v1/main_materials")
    suspend fun loadCards(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String
    ): Response<BaseListingModel<RowsModel>>

    @GET("api/v1/selections")
    suspend fun loadCollections(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String
    ): Response<BaseListingModel<RowsModel>>

    @GET("api/v1/educational_materials")
    suspend fun loadSelectionsData(
        @Query("limit") limit: Int,
        @Query("selection_id") selectionId: Int,
        @Query("page") page: Int
    ): Response<BaseListingModel<RowsModel>>

    @GET("api/v1/articles")
    suspend fun loadNews(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String
    ): Response<BaseListingModel<RowsModel>>

    @GET("api/v1/advices/1")
    suspend fun adviceOfDay(
    ): AdviceOfDayModel
}


