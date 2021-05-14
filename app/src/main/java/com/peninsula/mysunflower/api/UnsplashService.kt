package com.peninsula.mysunflower.api

import com.peninsula.mysunflower.BuildConfig
import com.peninsula.mysunflower.data.UnsplashSearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import kotlin.math.log

interface UnsplashService {
    /**
     * 搜索图片
     * @param query String 关键字
     * @param page Int 页码
     * @param perPage Int 每页数量
     * @param clientId String 客户端id
     * @return UnsplashSearchResponse 响应体
     */
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String = BuildConfig.UNSPLASH_ACCESS_KEY
    ): UnsplashSearchResponse

    companion object{
        private const val BASE_URL = "https://api.unsplash.com/"

        /**
         * 创建retrofit请求
         * @return UnsplashService
         */
        fun create():UnsplashService{
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UnsplashService::class.java)
        }

    }
}