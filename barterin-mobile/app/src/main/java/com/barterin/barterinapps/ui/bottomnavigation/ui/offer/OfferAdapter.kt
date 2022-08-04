package com.barterin.barterinapps.ui.bottomnavigation.ui.offer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.remote.response.DataItem
import com.barterin.barterinapps.data.remote.response.OfferData
import com.barterin.barterinapps.ui.bottomnavigation.ui.home.BarterItemsAdapter
import com.bumptech.glide.Glide

class OfferAdapter : RecyclerView.Adapter<OfferAdapter.ViewHolder>()  {

    private val dataList = ArrayList<OfferData>()
    var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(data: List<OfferData>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemName: TextView = itemView.findViewById(R.id.txt_nameItem_offer)
        private val penjual: TextView = itemView.findViewById(R.id.txt_penjual_offer)
        private val location: TextView = itemView.findViewById(R.id.txt_seller_location_offer)
        private val imgItem: ImageView = itemView.findViewById(R.id.img_cart_offer)
        private val button: Button = itemView.findViewById(R.id.button_add_offer)
        private val statusBarang: TextView = itemView.findViewById(R.id.txt_status_barang_offer)


        fun bind(user: OfferData) {
            itemName.text = user.barang.name
            penjual.text = user.barang.user
            location.text = user.barang.region

            when (user.barang.status) {
                "0" -> {
                    statusBarang.text = "Available"
                }
                "1" -> {
                    statusBarang.text = "Already bartered"
                }
                "2" -> {
                    statusBarang.text = "Was Removed"
                }
                else -> {
                    statusBarang.text = "Unknown"
                }
            }


            Glide.with(itemView.context)
                .load(user.barang.image)
                .into(imgItem)

            button.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: OfferData)
    }


}