package com.barterin.barterinapps.ui.additem

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.remote.response.CategoriesResult
import com.barterin.barterinapps.databinding.ListCategoryItemBinding

class ListCategoryAdapter : androidx.recyclerview.widget.ListAdapter<CategoriesResult, ListCategoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding = ListCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataAddress = getItem(position)
        holder.bind(dataAddress)
    }

    class ViewHolder(binding: ListCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var textCategory: TextView = itemView.findViewById(R.id.text_list_category)

        fun bind(dataAddress: CategoriesResult) {
            textCategory.text = dataAddress.name
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<CategoriesResult> =
            object : DiffUtil.ItemCallback<CategoriesResult>() {
                override fun areItemsTheSame(oldUser: CategoriesResult, newUser: CategoriesResult): Boolean {
                    return oldUser.id == newUser.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldUser: CategoriesResult,
                    newUser: CategoriesResult
                ): Boolean {
                    return oldUser == newUser
                }
            }
    }
}