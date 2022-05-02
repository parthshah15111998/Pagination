package com.example.pagination.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagination.databinding.ItemRowBinding
import com.example.pagination.modelClass.MyData

class MyAdapter(val context:Context,val userList:ArrayList<MyData.MyDataItem>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
       val itemRowBinding=ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemRowBinding)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.binding.item=userList[position]
    }

    override fun getItemCount(): Int {
       return userList.size
    }
}