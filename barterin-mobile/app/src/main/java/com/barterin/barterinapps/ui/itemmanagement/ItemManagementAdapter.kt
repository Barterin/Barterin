package com.barterin.barterinapps.ui.itemmanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.remote.response.GetMyItems
import com.bumptech.glide.Glide

class ItemManagementAdapter : RecyclerView.Adapter<ItemManagementAdapter.ViewHolder>() {


    private val dataList = ArrayList<GetMyItems>()

    var onItemClickCallback: ItemManagementAdapter.OnItemClickCallback? = null

    fun setOnItemClickCallBack(onItemClickCallback: ItemManagementAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    fun setList(data: List<GetMyItems>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemManagementAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_management, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemManagementAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemName: TextView = itemView.findViewById(R.id.txt_nameItem_update)
        private val penjual: TextView = itemView.findViewById(R.id.txt_penjual_update)
        private val seller: TextView = itemView.findViewById(R.id.txt_seller_location_update)
        private val buttonAdd : Button = itemView.findViewById(R.id.button_add_update)
        private val imgCart : ImageView = itemView.findViewById(R.id.img_cart_update)
        private val imgDelete : ImageView = itemView.findViewById(R.id.img_delete_update)


        fun bind(user: GetMyItems) {
            itemName.text = user.name
            penjual.text = user.name
            seller.text = user.address_item

            Glide.with(itemView.context)
                .load(user.image[0])
                .into(imgCart)

            buttonAdd.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            imgDelete.setOnClickListener {
                if (itemView.context is ItemManagementActivity) {
                    (itemView.context as ItemManagementActivity).deleteItem(user.id)
                }
            }

        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GetMyItems)
    }

}