package com.example.sambo.data.repository

import com.example.sambo.data.modelcourses.MainCourseModel
import com.example.sambo.data.modelcourses.Rows
import com.example.sambo.data.interactors.SamboInteractor
import com.example.sambo.data.model.advice.AdviceOfDayModel
import com.example.sambo.data.model.cards.CardsModel
import com.example.sambo.data.model.news.NewsModel
import com.example.sambo.data.modelBottomSheet.BottomSheetModel
import retrofit2.Response

interface SamboRepository {
    suspend fun loadData(limit: Int, page: Int,categoryId: Int) : MainCourseModel<Rows>
    suspend fun loadCategory(limit: Int,page: Int)  : Response<BottomSheetModel>
    suspend fun loadCards(limit: Int, page: Int): Response<CardsModel>
    suspend fun loadCollections(limit: Int, page: Int): Response<CardsModel>
    suspend fun loadNews(limit: Int, page: Int): Response<NewsModel>
    suspend fun loadSelectionsData(limit: Int, selectionId : Int, page: Int): Response<CardsModel>
    suspend fun adviceOfDay(): AdviceOfDayModel
}
class SamboRepositoryImpl(private val network : SamboInteractor) : SamboRepository {

    override suspend fun loadData(limit: Int, page: Int,categoryId: Int): MainCourseModel<Rows> {    // для подргузк данных при клике категории
        return if (categoryId == -1){   //-1 значен по умолчанию для подгрузки  оперделенных данных при выборе категории
            network.loadData(limit=limit,page = page)
        } else {
            network.loadDataWithCategoryId(limit=limit,page = page,categoryId = categoryId)
        }
    }

    override suspend fun loadCategory(limit: Int, page: Int): Response<BottomSheetModel>{
        return network.loadCategory(limit,page)
    }

    override suspend fun loadCards(limit: Int, page: Int): Response<CardsModel> {
        return network.loadCards(limit, page)
    }

    override suspend fun loadCollections(limit: Int, page: Int): Response<CardsModel> {
        return network.loadCollections(limit, page)
    }

    override suspend fun loadNews(limit: Int, page: Int): Response<NewsModel> {
        return network.loadNews(limit, page)
    }

    override suspend fun loadSelectionsData(
        limit: Int,
        selectionId: Int,
        page: Int
    ): Response<CardsModel> {
        return network.loadSelectionsData(limit, selectionId, page)
    }

    override suspend fun adviceOfDay(): AdviceOfDayModel {
        return network.adviceOfDay()
    }
}