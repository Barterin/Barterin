package com.barterin.barterinapps.ui.detailitem

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityDetailItemBinding
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailItemBinding
    private lateinit var sharedpref: SharedPreferenceClass

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
        val itemName = intent.getStringExtra("name_item")
        val estPrice = intent.getStringExtra("purchasePrice_item")
        val description = intent.getStringExtra("description_item")
        val usedTime = intent.getStringExtra("usedTime_item")
        val location = intent.getStringExtra("region_item")
        val idUser = intent.getStringExtra("id_user")
        sharedpref = SharedPreferenceClass(this)

        binding.txtNamaBarang.text = itemName
        binding.txtHargaDetail.text = estPrice
        binding.txtDetailDescription.text = description
        binding.txtUsedTime.text = "Used Time: $usedTime"
        binding.txtLocation.text = "Location : $location"

        binding.btnAddToCart.setOnClickListener {

            val id = intent.getStringExtra("id_item")
            if (id != null) {
                addToCart(id)
            }

        }

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




    }

    private fun addToCart(itemsId: String) {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailItemViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        viewModel.addToCart(sharedpref.getToken(), itemsId).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar11.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar11.visibility = View.GONE
                        val message = result.data.message
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                    is Result.Error -> {
                        binding.progressBar11.visibility = View.GONE
                        Toast.makeText(
                            this,
                            resources.getString(R.string.text_error) + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
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


}