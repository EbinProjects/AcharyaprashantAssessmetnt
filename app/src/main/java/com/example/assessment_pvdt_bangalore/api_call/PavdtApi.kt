package com.example.assessment_pvdt_bangalore.api_call

import MediaCoverage


import com.example.assessment_pvdt_bangalore.util.ConstantsValues
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PavdtApi {

    @GET("api/v2/content/misc/media-coverages")
    fun getApiResponses(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Call<List<MediaCoverage>>

    companion object {
        fun create(): PavdtApi {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            val client: OkHttpClient =
                OkHttpClient.Builder().addInterceptor { chain ->
                    val newRequest: Request = chain.request().newBuilder()
                        .build()
                    chain.proceed(newRequest)
                }.addInterceptor(logger)
                    .build()

            return Retrofit.Builder()
                .baseUrl(ConstantsValues.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PavdtApi::class.java)
        }
    }

}