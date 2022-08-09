package com.barterin.barterinapps.ui.searchresult

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.data.remote.response.DataItem
import com.barterin.barterinapps.databinding.ActivityLoginBinding
import com.barterin.barterinapps.databinding.ActivitySearchResultBinding
import com.barterin.barterinapps.ui.bottomnavigation.ui.home.BarterItemsAdapter
import com.barterin.barterinapps.ui.bottomnavigation.ui.home.HomeViewModel
import com.barterin.barterinapps.ui.detailitem.DetailItemActivity
import com.barterin.barterinapps.viewmodel.ViewModelFactory

class SearchResultActivity : AppCompatActivity() {

    private var _binding: ActivitySearchResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val query = intent.getStringExtra("query")

        Toast.makeText(this@SearchResultActivity, query, Toast.LENGTH_SHORT).show()

        setupView()

        getList(query.toString())

    }

    private fun getList(query: String) {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        val barteritemsAdapter = BarterItemsAdapter()

        viewModel.searchBarterItem(query).observe(this) { result ->

            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar14.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        val data = result.data
                        barteritemsAdapter.setList(data)
                        binding.progressBar14.visibility = View.GONE
                    }
                    is Result.Error -> {
                        Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        with(binding.rvSearchresult) {
            this.layoutManager = GridLayoutManager(this@SearchResultActivity, 2)
            this.setHasFixedSize(true)
            this.adapter?.notifyDataSetChanged()
            this.adapter = barteritemsAdapter
        }

        barteritemsAdapter.setOnItemClickCallBack(object : BarterItemsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                // Parcelable not support type or json object value yet,
                // so i must send data one by one :v fucking hell
                Intent(this@SearchResultActivity, DetailItemActivity::class.java).also {
                    it.putExtra("id_user", data.user.id)
                    it.putExtra("name_user", data.user.name)
                    it.putExtra("phone_user", data.user.phone)
                    it.putExtra("id_category", data.category.id)
                    it.putExtra("name_category", data.category.name)
                    it.putExtra("id_type", data.type.id)
                    it.putExtra("name_type", data.type.name)
                    it.putExtra("id_item", data.item.id)
                    it.putExtra("image_item_1", data.item.image[0])
                    it.putExtra("image_item_2", data.item.image[1])
                    it.putExtra("image_item_3", data.item.image[2])
                    it.putExtra("name_item", data.item.name)
                    it.putExtra("description_item", data.item.description)
                    it.putExtra("usedTime_item", data.item.used_time)
                    it.putExtra("purchasePrice_item", data.item.purchase_price)
                    it.putExtra("address_item", data.item.address_item)
                    it.putExtra("region_item", data.item.address_region)
                    it.putExtra("longitude_item", data.item.address_longitude)
                    it.putExtra("latitude_item", data.item.address_latitude)
                    it.putExtra("bidder", data.item.bidder)
                    startActivity(it)
                }
            }
        })
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