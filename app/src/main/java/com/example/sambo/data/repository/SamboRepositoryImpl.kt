package com.example.sambo.data.repository

import com.example.sambo.data.interactors.SamboInteractor
import com.example.sambo.data.model.advice.AdviceOfDayModel
import com.example.sambo.data.model.listing.BaseListingModel
import com.example.sambo.data.model.listing.RowsModel

import retrofit2.Response

interface SamboRepository {
    suspend fun loadData(limit: Int, page: Int,categoryId: Int) : BaseListingModel<RowsModel>
    suspend fun loadCategory(limit: Int,page: Int)  : Response<BaseListingModel<RowsModel>>
    suspend fun loadCards(limit: Int, page: Int): Response<BaseListingModel<RowsModel>>
    suspend fun loadCollections(limit: Int, page: Int): Response<BaseListingModel<RowsModel>>
    suspend fun loadNews(limit: Int, page: Int): Response<BaseListingModel<RowsModel>>
    suspend fun loadSelectionsData(limit: Int, selectionId : Int, page: Int): Response<BaseListingModel<RowsModel>>
    suspend fun adviceOfDay(): AdviceOfDayModel
}
class SamboRepositoryImpl(private val network : SamboInteractor) : SamboRepository {

    override suspend fun loadData(limit: Int, page: Int,categoryId: Int): BaseListingModel<RowsModel> {
        return if (categoryId == -1){
            network.loadData(limit=limit,page = page)
        } else {
            network.loadDataWithCategoryId(limit=limit,page = page,categoryId = categoryId)
        }
    }

    override suspend fun loadCategory(limit: Int, page: Int): Response<BaseListingModel<RowsModel>>{
        return network.loadCategory(limit,page)
    }

    override suspend fun loadCards(limit: Int, page: Int): Response<BaseListingModel<RowsModel>> {
        return network.loadCards(limit, page)
    }

    override suspend fun loadCollections(limit: Int, page: Int): Response<BaseListingModel<RowsModel>> {
        return network.loadCollections(limit, page)
    }

    override suspend fun loadNews(limit: Int, page: Int): Response<BaseListingModel<RowsModel>> {
        return network.loadNews(limit, page)
    }

    override suspend fun loadSelectionsData(
        limit: Int,
        selectionId: Int,
        page: Int
    ): Response<BaseListingModel<RowsModel>> {
        return network.loadSelectionsData(limit, selectionId, page)
    }

    override suspend fun adviceOfDay(): AdviceOfDayModel {
        return network.adviceOfDay()
    }
}