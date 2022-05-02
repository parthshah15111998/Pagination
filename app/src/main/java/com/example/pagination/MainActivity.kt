package com.example.pagination

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagination.adapter.MyAdapter
import com.example.pagination.api.ApiInterface
import com.example.pagination.databinding.ActivityMainBinding
import com.example.pagination.modelClass.MyData
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter
    lateinit var userList: ArrayList<MyData.MyDataItem>
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        getData()
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!isLoading) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == userList.size - 1){
                        getData()
                    }
                }

            }
        })
    }

    private fun setAdapter() {
        userList = arrayListOf<MyData.MyDataItem>()
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
        myAdapter = MyAdapter(baseContext, userList)
        binding.recyclerView.adapter = myAdapter
    }

    fun getData() {
        isLoading = true
        binding.loading.visibility = View.VISIBLE
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<ArrayList<MyData.MyDataItem>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ArrayList<MyData.MyDataItem>?>,
                response: Response<ArrayList<MyData.MyDataItem>?>
            ) {
                val responseBody = response.body()!!
                userList.addAll(responseBody)
                binding.loading.postDelayed(Runnable {
                    myAdapter.notifyDataSetChanged()
                    isLoading = false
                    binding.loading.visibility = View.GONE
                }, 3000)


            }

            override fun onFailure(call: Call<ArrayList<MyData.MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "on failure" + t.message)
            }
        })
    }
}