package com.example.sambo.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {


  fun buildRetrofit(): SamboService {
       return Retrofit.Builder()
            .baseUrl("https://api.sambo.beta.trinitydigital.ru/")
            .addConverterFactory(GsonConverterFactory.create())
           .client(getClient())
           .build()
            .create(SamboService::class.java)

        //https://api.sambo.beta.trinitydigital.ru/api/v1/ categories?limit=20&page=1&order={%22rank%22:%22asc%22}

        //api/v1/educational_materials?limit=20&page=1&category_id=11&order= "{\"id\":\"asc\"}"
    }

    private fun  getClient(): OkHttpClient {  // Чисто для оображен пагинац в logcate
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(provideLoggingInterceptor())
            .build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }


}