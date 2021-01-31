package com.example.sambo.data.interactors

import com.example.sambo.data.model.advice.AdviceOfDayModel
import com.example.sambo.data.model.cards.CardsModel
import com.example.sambo.data.model.cards.RowsItem
import com.example.sambo.data.model.news.NewsModel

import com.example.sambo.data.modelBottomSheet.BottomSheetModel
import com.example.sambo.data.modelcourses.MainCourseModel
import com.example.sambo.data.remote.SamboService
import retrofit2.Response


interface SamboInteractor{
    suspend fun loadData(limit: Int, page: Int) : MainCourseModel<RowsItem>
    suspend fun loadCategory(limit: Int,page: Int)  : Response<BottomSheetModel>
    suspend fun loadDataWithCategoryId(limit: Int,page: Int,categoryId: Int) : MainCourseModel<RowsItem>  // для подргузк данных при клике категории
    suspend fun loadCards(limit: Int, page: Int): Response<CardsModel>
    suspend fun loadCollections(limit: Int, page: Int): Response<CardsModel>
    suspend fun loadNews(limit: Int, page: Int): Response<NewsModel>
    suspend fun loadSelectionsData(limit: Int, selectionId : Int, page: Int): Response<CardsModel>
    suspend fun adviceOfDay(): AdviceOfDayModel
}

class SamboInteractorImpl (private val service : SamboService) : SamboInteractor{
    override suspend fun loadData(limit: Int, page: Int): MainCourseModel<RowsItem> {
        return service.getCourses(limit = limit, page = page,order = "{\"id\":\"asc\"}")
    }

    override suspend fun loadCategory(limit: Int, page: Int): Response<BottomSheetModel> {
        return service.getBottomSheet(limit=20,page = 1,order ="{\"id\":\"asc\"}")
    }

    override suspend fun loadDataWithCategoryId(  // для подргузк данных при клике категории
        limit: Int,
        page: Int,
        categoryId: Int
    ): MainCourseModel<RowsItem> {
       return service.getCategoryId(limit = limit, page = page,order = "{\"id\":\"asc\"}",categoryId = categoryId)
    }
    override suspend fun loadCards(limit: Int, page: Int): Response<CardsModel> {
        return service.loadCards(limit = limit, page = page, order = "{\"rank\":\"asc\"}")
    }

    override suspend fun loadCollections(limit: Int, page: Int): Response<CardsModel> {
        return service.loadCollections(limit = limit, page = page, order = "{\"rank\":\"asc\"}")
    }

    override suspend fun loadNews(limit: Int, page: Int): Response<NewsModel> {
        return service.loadNews(limit = limit, page = page, order = "{\"created_at\":\"asc\"}")
    }

    override suspend fun loadSelectionsData(
        limit: Int,
        selectionId: Int,
        page: Int
    ): Response<CardsModel> {
        return service.loadSelectionsData(limit, selectionId, page)
    }

    override suspend fun adviceOfDay(): AdviceOfDayModel {
        return service.adviceOfDay()
    }
}