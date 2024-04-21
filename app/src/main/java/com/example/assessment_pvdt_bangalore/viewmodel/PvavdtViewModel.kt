package com.example.assessment_pvdt_bangalore.viewmodel

import MediaCoverage
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment_pvdt_bangalore.repositort.PvdtRepository
import com.example.assessment_pvdt_bangalore.util.ConstantsValues
import com.example.assessment_pvdt_bangalore.util.Fileutils
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class PvavdtViewModel @Inject constructor(
    private val repository: PvdtRepository
) : ViewModel() {



    var  currentpage =0
    var offset =0
    var pvadtLists = mutableStateOf<List<MediaCoverage>>(listOf())
    var  loadError = mutableStateOf("")
    var  isLoading = mutableStateOf(false)
    var  reachEnded = mutableStateOf(false)


    fun loadApiContents(){
        viewModelScope.launch(Dispatchers.IO) {
           isLoading.value=true
            offset =currentpage*ConstantsValues.PAGE_SIZE
            val call = repository.PvdtResponse(offset = offset, limit = ConstantsValues.PAGE_SIZE)
            try {
                call.enqueue(object : Callback<List<MediaCoverage>> {
                    override fun onResponse(call: Call<List<MediaCoverage>>, response: Response<List<MediaCoverage>>) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            reachEnded.value = currentpage * ConstantsValues.PAGE_SIZE >= (data?.count() ?: 0)
                            currentpage++
                            loadError.value = ""
                            isLoading.value = false
                            pvadtLists.value += data as List<MediaCoverage>
                        } else {
                            isLoading.value=false
                        }
                    }

                    override fun onFailure(call: Call<List<MediaCoverage>>, t: Throwable) {
                        Log.e("TAG", "No  onResponse: ${t.toString()}" )
                        isLoading.value=true
                        loadError.value="Ops,Api fetching error occurred!!!"
                        currentpage++
                        reachEnded.value= true
                    }
                })
            } catch (e: Exception) {
                isLoading.value=true
                loadError.value="Ops,Api fetching error occurred!!!"
                reachEnded.value= true
                Log.e("TAG", "No  onResponse: ${e.toString()}" )
              throw e
            }



        }
    }

}