package com.barterin.barterinapps.ui.detailitem

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.remote.response.DataItem
import com.barterin.barterinapps.databinding.ActivityDetailItemBinding
import com.bumptech.glide.Glide

class DetailItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        setSupportActionBar(findViewById(R.id.toolbar))

//        it.putExtra("id_user", data.user.id)
//        it.putExtra("name_user", data.user.name)
//        it.putExtra("phone_user", data.user.phone)
//        it.putExtra("id_category", data.category.id)
//        it.putExtra("name_category", data.category.name)
//        it.putExtra("id_type", data.type.id)
//        it.putExtra("name_type", data.type.name)
//        it.putExtra("id_item", data.item.id)
//        it.putExtra("image_item_1", data.item.image[0])
//        it.putExtra("image_item_2", data.item.image[1])
//        it.putExtra("image_item_3", data.item.image[2])
//        it.putExtra("name_item", data.item.name)
//        it.putExtra("description_item", data.item.description)
//        it.putExtra("usedTime_item", data.item.used_time)
//        it.putExtra("purchasePrice_item", data.item.purchase_price)
//        it.putExtra("address_item", data.item.address_item)
//        it.putExtra("region_item", data.item.address_region)
//        it.putExtra("longitude_item", data.item.address_longitude)
//        it.putExtra("latitude_item", data.item.address_latitude)
//        it.putExtra("bidder", data.item.bidder)

        val data = intent.getStringExtra("id_user")
        val image1 = intent.getStringExtra("image_item_1")
        val image2 = intent.getStringExtra("image_item_2")
        val image3 = intent.getStringExtra("image_item_3")

        Glide.with(this)
            .load(image1)
            .into(binding.previewimage)

        Glide.with(this)
            .load(image1)
            .into(binding.imagedetail1)

        Glide.with(this)
            .load(image2)
            .into(binding.imagedetail2)

        Glide.with(this)
            .load(image3)
            .into(binding.imagedetail3)

        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()


    }


    private fun setupView() {

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}