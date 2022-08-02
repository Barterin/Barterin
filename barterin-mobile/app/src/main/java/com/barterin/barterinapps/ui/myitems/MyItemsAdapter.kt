package com.barterin.barterinapps.ui.myitems

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

class MyItemsAdapter : RecyclerView.Adapter<MyItemsAdapter.ViewHolder>() {

    private val dataList = ArrayList<GetMyItems>()
    var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(data: List<GetMyItems>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemsAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_myitems, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyItemsAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemName: TextView = itemView.findViewById(R.id.txt_nameItem_myItem)
        private val penjual: TextView = itemView.findViewById(R.id.txt_penjual_myItems)
        private val seller: TextView = itemView.findViewById(R.id.txt_seller_location_myItems)
        private val buttonAdd : Button = itemView.findViewById(R.id.button_choose_item)
        private val imgCart : ImageView = itemView.findViewById(R.id.img_cart_myItems)


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
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GetMyItems)
    }

}