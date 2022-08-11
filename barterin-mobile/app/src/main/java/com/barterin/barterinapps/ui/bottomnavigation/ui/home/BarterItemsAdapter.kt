package com.barterin.barterinapps.ui.bottomnavigation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.remote.response.DataItem
import com.bumptech.glide.Glide

class BarterItemsAdapter : RecyclerView.Adapter<BarterItemsAdapter.ViewHolder>() {

    private val dataList = ArrayList<DataItem>()
    var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarterItemsAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_barter, parent, false)
        return ViewHolder(view)
    }

    fun setList(data: List<DataItem>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BarterItemsAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemName: TextView = itemView.findViewById(R.id.text_item_name)
        private val usedTime: TextView = itemView.findViewById(R.id.text_used_time)
        private val purchasePrice: TextView = itemView.findViewById(R.id.txt_purchase_price)
        private val imgItem: ImageView = itemView.findViewById(R.id.img_barter_item)
        private val location: TextView = itemView.findViewById(R.id.txt_location_item)
        private val bidder: TextView = itemView.findViewById(R.id.txt_bidder)

        fun bind(user: DataItem) {
            itemName.text = user.item.name
            usedTime.text = user.item.used_time
            purchasePrice.text = user.item.purchase_price
            location.text = user.item.address_region
            bidder.text = "Bidder ${user.item.bidder}"

            Glide.with(itemView.context)
                .load(user.item.image[0])
                .into(imgItem)

            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }

}