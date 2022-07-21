package com.barterin.barterinapps.ui.bottomnavigation.ui.home


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barterin.barterinapps.R

import com.barterin.barterinapps.data.remote.response.CategoriesResult
import com.barterin.barterinapps.databinding.AddressItemBinding
import com.barterin.barterinapps.databinding.ItemCategoryBinding
import com.bumptech.glide.Glide

class CatgoryAdapter : RecyclerView.Adapter<CatgoryAdapter.ViewHolder>() {

    private val dataList = ArrayList<CategoriesResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    fun setList(data: List<CategoriesResult>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val categoryName: TextView = itemView.findViewById(R.id.txt_title_category)

        fun bind(user: CategoriesResult) {
            categoryName.text = user.name
        }

    }


    override fun getItemCount(): Int = dataList.size


}