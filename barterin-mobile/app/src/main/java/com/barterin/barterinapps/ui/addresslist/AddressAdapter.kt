package com.barterin.barterinapps.ui.addresslist

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.remote.response.AddressResult
import com.barterin.barterinapps.databinding.AddressItemBinding
import com.barterin.barterinapps.ui.updateaddress.UpdateAddressActivity

class AddressAdapter : ListAdapter<AddressResult, AddressAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding = AddressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataAddress = getItem(position)
        holder.bind(dataAddress)
    }

    class ViewHolder(binding: AddressItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var label: TextView = itemView.findViewById(R.id.txt_label)
        private var receiver: TextView = itemView.findViewById(R.id.txt_receiver)
        private var phoneNumber: TextView = itemView.findViewById(R.id.txt_phoneNumber)
        private var fullAddress: TextView = itemView.findViewById(R.id.txt_fullAddress)
        private var postCode: TextView = itemView.findViewById(R.id.txt_kodepos)
        private var btnEdit: ImageView = itemView.findViewById(R.id.btn_edit)
        private var btnDelete: ImageView = itemView.findViewById(R.id.btn_delete)

        fun bind(dataAddress: AddressResult) {
            label.text = dataAddress.label
            receiver.text = dataAddress.penerima
            phoneNumber.text = dataAddress.nohp
            fullAddress.text = dataAddress.alamat_lengkap
            postCode.text = dataAddress.kode_pos

            btnDelete.setOnClickListener {
                if (itemView.context is AddressActivity) {
                    (itemView.context as AddressActivity).deleteAddress(dataAddress.id)
                }
            }

            btnEdit.setOnClickListener {
                val moveEdit = Intent(itemView.context, UpdateAddressActivity::class.java)
                moveEdit.putExtra("dataAddress", dataAddress)
                itemView.context.startActivity(moveEdit)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<AddressResult> =
            object : DiffUtil.ItemCallback<AddressResult>() {
                override fun areItemsTheSame(oldUser: AddressResult, newUser: AddressResult): Boolean {
                    return oldUser.id == newUser.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldUser: AddressResult,
                    newUser: AddressResult
                ): Boolean {
                    return oldUser == newUser
                }
            }
    }

}