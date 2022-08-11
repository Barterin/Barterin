package com.barterin.barterinapps.ui.bottomnavigation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.remote.response.CategoriesResult

class CatgoryAdapter : RecyclerView.Adapter<CatgoryAdapter.ViewHolder>() {

    private val dataList = ArrayList<CategoriesResult>()
    var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

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
        private val totalItems: TextView = itemView.findViewById(R.id.txt_total_items)
        private val contraint: ConstraintLayout = itemView.findViewById(R.id.contraint_category)

        fun bind(user: CategoriesResult) {
            categoryName.text = user.name
            totalItems.text = "${user.count} Item"

            if (user.slug == "elektronik") {
                contraint.setBackgroundResource(R.drawable.ic_computer)
            } else if (user.slug == "fashion-muslim") {
                contraint.setBackgroundResource(R.drawable.ic_fashion)
            }
            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickCallback {
        fun onItemClicked(data: CategoriesResult)
    }


}