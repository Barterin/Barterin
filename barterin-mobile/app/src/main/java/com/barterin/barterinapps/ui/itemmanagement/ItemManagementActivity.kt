package com.barterin.barterinapps.ui.itemmanagement

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.data.remote.response.GetMyItems
import com.barterin.barterinapps.databinding.ActivityItemManagementBinding
import com.barterin.barterinapps.ui.updateitem.UpdateItemActivity
import com.barterin.barterinapps.viewmodel.ViewModelFactory

class ItemManagementActivity : AppCompatActivity() {

    private var _binding: ActivityItemManagementBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityItemManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        getMyItems()

    }

    private fun getMyItems() {
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[ItemManagementViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)
        val itemManagementAdapter = ItemManagementAdapter()

        viewModel.getMyItems(sharedpref.getToken()).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar15.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        val data = result.data
                        itemManagementAdapter.setList(data)
                        binding.progressBar15.visibility = View.GONE
                    }
                    is Result.Error -> {
                        Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                        binding.progressBar15.visibility = View.GONE
                    }
                }
            }
        }
        binding.rvManagement.apply {
            layoutManager = LinearLayoutManager(this@ItemManagementActivity)
            setHasFixedSize(true)
            adapter?.notifyDataSetChanged()
            adapter = itemManagementAdapter
        }

        itemManagementAdapter.setOnItemClickCallBack(object : ItemManagementAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GetMyItems) {
                Intent(this@ItemManagementActivity, UpdateItemActivity::class.java).also {
                    it.putExtra("id_item", data.id)
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

    fun deleteItem(id: String) {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[ItemManagementViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        viewModel.deleteItem(sharedpref.getToken(), id).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar15.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar15.visibility = View.GONE
                        this.recreate()
                        Toast.makeText(this, result.data.message, Toast.LENGTH_SHORT).show()
                    }
                    is Result.Error -> {
                        binding.progressBar15.visibility = View.GONE
                        Toast.makeText(
                            this,
                            result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

}