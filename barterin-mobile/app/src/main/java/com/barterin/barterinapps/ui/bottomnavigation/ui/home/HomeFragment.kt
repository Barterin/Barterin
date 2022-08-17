package com.barterin.barterinapps.ui.bottomnavigation.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.data.remote.response.CategoriesResult
import com.barterin.barterinapps.data.remote.response.DataItem
import com.barterin.barterinapps.databinding.FragmentHomeBinding
import com.barterin.barterinapps.ui.cart.CartActivity
import com.barterin.barterinapps.ui.detailitem.DetailItemActivity
import com.barterin.barterinapps.ui.searchresult.SearchResultActivity
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import java.io.Serializable

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            getCategoryList()
            getBarterList()
        } else {
            Toast.makeText(requireContext(), "yahaha", Toast.LENGTH_SHORT).show()
        }

        binding.searchviewItems.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                val moveIntent = Intent(requireContext(), SearchResultActivity::class.java).apply {
                    putExtra("query", query)
                }
                startActivity(moveIntent)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.navCart.setOnClickListener {
            startActivity(Intent(requireContext(), CartActivity::class.java))
        }

    }

    private fun getBarterList() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        sharedpref = SharedPreferenceClass(requireContext())
        val barteritemsAdapter = BarterItemsAdapter()
        viewModel.getAllBarterItems().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar17.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        val data = result.data
                        barteritemsAdapter.setList(data)
                        binding.progressBar17.visibility = View.GONE
                        Log.d("myresult", result.data.toString())
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            requireContext(),
                        result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBar17.visibility = View.GONE
                    }
                }
            }
        }
        with(binding.rvBarteritem) {
            this.layoutManager = GridLayoutManager(context, 2)
            this.setHasFixedSize(true)
            this.adapter?.notifyDataSetChanged()
            this.adapter = barteritemsAdapter
        }

        barteritemsAdapter.setOnItemClickCallBack(object : BarterItemsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                // Parcelable not support type or json object value yet,
                // so i must send data one by one :v fucking hell
                Intent(requireContext(), DetailItemActivity::class.java).also {
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

    private fun getCategoryList() {

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        sharedpref = SharedPreferenceClass(requireContext())
        val categoryAdapter = CatgoryAdapter()
        viewModel.getCategoryList(sharedpref.getToken()).observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar17.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar17.visibility = View.GONE
                        val data = result.data
                        categoryAdapter.setList(data)
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            requireContext(),
                            result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBar17.visibility = View.GONE
                    }
                }
            }
        }
        with(binding.rvCategory) {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.setHasFixedSize(true)
            this.adapter?.notifyDataSetChanged()
            this.adapter = categoryAdapter
        }

        categoryAdapter.setOnItemClickCallBack(object : CatgoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CategoriesResult) {
                val moveIntent = Intent(requireContext(), SearchResultActivity::class.java).apply {
                    putExtra("query", data.name)
                }
                startActivity(moveIntent)
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}