package com.example.sambo.data.interactors

import com.example.sambo.data.model.advice.AdviceOfDayModel
import com.example.sambo.data.model.listing.BaseListingModel
import com.example.sambo.data.model.listing.RowsModel

import com.example.sambo.data.remote.SamboService
import retrofit2.Response

interface SamboInteractor{
    suspend fun loadData(limit: Int, page: Int) : BaseListingModel<RowsModel>
    suspend fun loadCategory(limit: Int,page: Int)  : Response<BaseListingModel<RowsModel>>
    suspend fun loadDataWithCategoryId(limit: Int,page: Int,categoryId: Int) : BaseListingModel<RowsModel>
    suspend fun loadCards(limit: Int, page: Int): Response<BaseListingModel<RowsModel>>
    suspend fun loadCollections(limit: Int, page: Int): Response<BaseListingModel<RowsModel>>
    suspend fun loadNews(limit: Int, page: Int): Response<BaseListingModel<RowsModel>>
    suspend fun loadSelectionsData(limit: Int, selectionId : Int, page: Int): Response<BaseListingModel<RowsModel>>
    suspend fun adviceOfDay(): AdviceOfDayModel
}

class SamboInteractorImpl (private val service : SamboService) : SamboInteractor{
    override suspend fun loadData(limit: Int, page: Int): BaseListingModel<RowsModel> {
        return service.getCourses(limit = limit, page = page,order = "{\"id\":\"asc\"}")
    }

    override suspend fun loadCategory(limit: Int, page: Int): Response<BaseListingModel<RowsModel>> {
        return service.getBottomSheet(limit=20,page = 1,order ="{\"id\":\"asc\"}")
    }

    override suspend fun loadDataWithCategoryId(
        limit: Int,
        page: Int,
        categoryId: Int
    ): BaseListingModel<RowsModel> {
       return service.getCategoryId(limit = limit, page = page,order = "{\"id\":\"asc\"}",categoryId = categoryId)
    }
    override suspend fun loadCards(limit: Int, page: Int): Response<BaseListingModel<RowsModel>> {
        return service.loadCards(limit = limit, page = page, order = "{\"rank\":\"asc\"}")
    }

    override suspend fun loadCollections(limit: Int, page: Int): Response<BaseListingModel<RowsModel>> {
        return service.loadCollections(limit = limit, page = page, order = "{\"rank\":\"asc\"}")
    }

    override suspend fun loadNews(limit: Int, page: Int): Response<BaseListingModel<RowsModel>> {
        return service.loadNews(limit = limit, page = page, order = "{\"created_at\":\"asc\"}")
    }

    override suspend fun loadSelectionsData(
        limit: Int,
        selectionId: Int,
        page: Int
    ): Response<BaseListingModel<RowsModel>> {
        return service.loadSelectionsData(limit, selectionId, page)
    }

    override suspend fun adviceOfDay(): AdviceOfDayModel {
        return service.adviceOfDay()
    }
}