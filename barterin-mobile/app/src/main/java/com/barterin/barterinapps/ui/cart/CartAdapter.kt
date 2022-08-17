package com.barterin.barterinapps.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.remote.response.DataCartResult
import com.bumptech.glide.Glide

class CartAdapter : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val dataList = ArrayList<DataCartResult>()
    var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    fun setList(data: List<DataCartResult>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemName: TextView = itemView.findViewById(R.id.txt_nameItem_cart)
        private val penjual: TextView = itemView.findViewById(R.id.txt_penjual)
        private val seller: TextView = itemView.findViewById(R.id.txt_seller_location)
        private val buttonAdd : Button = itemView.findViewById(R.id.button_add_tawaran)
        private val imgCart : ImageView = itemView.findViewById(R.id.img_cart)
        private val deleteChart : ImageView = itemView.findViewById(R.id.btn_delete_chart)


        fun bind(user: DataCartResult) {
            itemName.text = user.barang.name
            penjual.text = user.barang.user
            seller.text = user.barang.region

            Glide.with(itemView.context)
                .load(user.barang.image)
                .into(imgCart)

            buttonAdd.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            deleteChart.setOnClickListener {
                if (itemView.context is CartActivity) {
                    (itemView.context as CartActivity).deleteChart(user.id)
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickCallback {
        fun onItemClicked(data: DataCartResult)
    }

}