package com.example.pagination.api

import com.example.pagination.modelClass.MyData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    fun getData():Call<ArrayList<MyData.MyDataItem>>

}