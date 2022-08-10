package com.barterin.barterinapps.ui.additem

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.remote.response.DataTypes

class ChooseTypeAdapter : RecyclerView.Adapter<ChooseTypeAdapter.ViewHolder>() {

    private val dataList = ArrayList<DataTypes>()

    var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseTypeAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_choose_category, parent, false)
        return ViewHolder(view)
    }

    fun clear() {
        dataList.clear()
    }

    fun setList(data: List<DataTypes>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ChooseTypeAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val categoryName: TextView = itemView.findViewById(R.id.txt_item_category)
        private val constrainItem: ConstraintLayout = itemView.findViewById(R.id.constraint_item)

        fun bind(user: DataTypes) {
            categoryName.text = user.name

            itemView.setOnClickListener {
                constrainItem.setBackgroundColor(Color.parseColor("#5395ee"))
                onItemClickCallback?.onItemClicked(user)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickCallback {
        fun onItemClicked(data: DataTypes)
    }

}