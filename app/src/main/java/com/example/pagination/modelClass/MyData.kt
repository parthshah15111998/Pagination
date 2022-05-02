package com.example.pagination.modelClass

class MyData : ArrayList<MyData.MyDataItem>(){
    data class MyDataItem(
        val body: String,
        val id: String,
        val title: String,
        val userId: String
    )
}