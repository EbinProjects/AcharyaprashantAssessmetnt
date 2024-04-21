package com.example.assessment_pvdt_bangalore.util

sealed class Fileutils<T>(val data :T? = null,val message : String? = null){
    class Success<T>(data: T): Fileutils<T>(data)
    class Error<T>(data: T? = null,message: String): Fileutils<T>(data,message)
}