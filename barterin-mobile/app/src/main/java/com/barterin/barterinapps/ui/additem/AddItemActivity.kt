package com.barterin.barterinapps.ui.additem

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.data.remote.response.AddressResult
import com.barterin.barterinapps.data.remote.response.CategoriesResult
import com.barterin.barterinapps.data.remote.response.DataTypes
import com.barterin.barterinapps.databinding.ActivityAddItemBinding
import com.barterin.barterinapps.ui.bottomnavigation.HomeActivity
import com.barterin.barterinapps.ui.bottomnavigation.ui.home.HomeViewModel
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import com.google.gson.TypeAdapter


class AddItemActivity : AppCompatActivity() {

    private var _binding: ActivityAddItemBinding? = null
    private val binding get() = _binding

    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupView()
        getCategoryList()
        showAddress()

        binding?.btnNextUpload?.setOnClickListener {
            moveWithData()
        }
    }

    private fun showAddress() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AddItemViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        val chooseAddressAdapter = ChooseAddressAdapter()

        viewModel.getDataAddress(sharedpref.getToken()).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding?.progressBar7?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding?.progressBar7?.visibility = View.GONE
                        val data = result.data
                        chooseAddressAdapter.setList(data)
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            this,
                            "error nih" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        with(binding?.rvChooseAddress) {
            this?.layoutManager = LinearLayoutManager(this@AddItemActivity, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter?.notifyDataSetChanged()
            this?.adapter = chooseAddressAdapter
        }

        chooseAddressAdapter.setOnItemClickCallBack(object : ChooseAddressAdapter.OnItemClickCallback {
            override fun onItemClicked(data: AddressResult) {
                val sharedPreference =  getSharedPreferences("id_preference",Context.MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putString("address_id",data.id)
                editor.commit()
            }

        })

    }

    private fun getCategoryList() {
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AddItemViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        val chooseCategoryAdapter = ChooseCategoryAdapter()

        viewModel.getCategoryList(sharedpref.getToken()).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding?.progressBar7?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        val data = result.data
                        chooseCategoryAdapter.setList(data)
                        binding?.progressBar7?.visibility = View.GONE
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            this,
                            result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                        binding?.progressBar7?.visibility = View.GONE
                    }
                }
            }
        }

        with(binding?.rvChooseCategory) {
            this?.layoutManager = LinearLayoutManager(this@AddItemActivity, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter?.notifyDataSetChanged()
            this?.adapter = chooseCategoryAdapter
        }

        chooseCategoryAdapter.setOnItemClickCallBack(object : ChooseCategoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CategoriesResult) {
                showType(data.id)
            }
        })

    }

    private fun showType(id: String) {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AddItemViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        val typeAdapter = ChooseTypeAdapter()

        typeAdapter.clear()

        viewModel.getTypeList(id).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding?.progressBar7?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        val data = result.data
                        typeAdapter.setList(data)
                        binding?.progressBar7?.visibility = View.GONE
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            this,
                            result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                        binding?.progressBar7?.visibility = View.GONE
                    }
                }
            }
        }

        binding?.rvChooseItemtype.apply {
            this?.layoutManager = LinearLayoutManager(this@AddItemActivity, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter?.notifyDataSetChanged()
            this?.adapter = typeAdapter
        }

        typeAdapter.setOnItemClickCallBack(object : ChooseTypeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataTypes) {
                val sharedPreference =  getSharedPreferences("id_preference",Context.MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putString("id_type",data.id)
                editor.commit()
            }
        })
    }


    private fun moveWithData() {
        val sharedPreference =  getSharedPreferences("id_preference",Context.MODE_PRIVATE)
        val intent = Intent(this, AddPhotoActivity::class.java)
        intent.putExtra("type", sharedPreference.getString("id_type", "kosong"))
        intent.putExtra("address", sharedPreference.getString("address_id", "kosong"))
        intent.putExtra("itemName", binding?.itemNameEditText?.text.toString())
        intent.putExtra("itemDescription", binding?.itemDescriptionEditText?.text.toString())
        intent.putExtra("usedTime", binding?.usedTimeEditText?.text.toString())
        intent.putExtra("priceRange", binding?.priceRangeEditText?.text.toString())
        startActivity(intent)
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

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

}