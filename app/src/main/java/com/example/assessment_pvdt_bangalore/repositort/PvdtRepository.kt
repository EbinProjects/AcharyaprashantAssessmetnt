package com.example.assessment_pvdt_bangalore.repositort

import MediaCoverage
import com.example.assessment_pvdt_bangalore.api_call.PavdtApi
import com.example.assessment_pvdt_bangalore.util.Fileutils
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject



class PvdtRepository@Inject constructor(
   private val api: PavdtApi
) {



    suspend fun  PvdtResponse(
        offset :Int,limit : Int
    ): Call<List<MediaCoverage>> {
        return     api.getApiResponses(offset,limit)
    }
}