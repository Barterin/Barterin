package com.barterin.barterinapps.ui.myitems

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.data.remote.response.GetMyItems
import com.barterin.barterinapps.databinding.ActivityLoginBinding
import com.barterin.barterinapps.databinding.ActivityMyItemsBinding
import com.barterin.barterinapps.ui.cart.CartViewModel
import com.barterin.barterinapps.viewmodel.ViewModelFactory

class MyItemsActivity : AppCompatActivity() {

    private var _binding: ActivityMyItemsBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMyItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        getMyItems()

    }

    private fun getMyItems() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[MyItemsViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)
        val myItemsAdapter = MyItemsAdapter()

        viewModel.getMyItems(sharedpref.getToken()).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar12.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        val data = result.data
                        myItemsAdapter.setList(data)
                        binding.progressBar12.visibility = View.GONE
                    }
                    is Result.Error -> {
                        Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                        binding.progressBar12.visibility = View.GONE
                    }
                }
            }
        }

        binding.rvMyitems.apply {
            layoutManager = LinearLayoutManager(this@MyItemsActivity)
            setHasFixedSize(true)
            adapter?.notifyDataSetChanged()
            adapter = myItemsAdapter
        }

        myItemsAdapter.setOnItemClickCallBack(object : MyItemsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GetMyItems) {
                val withId = intent.getStringExtra("id_items")
                viewModel.offerBarter(sharedpref.getToken(), withId.toString(), data.id).observe(this@MyItemsActivity) { result ->
                    if (result != null) {
                        when(result) {
                            is Result.Loading -> {
                                binding.progressBar12.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.progressBar12.visibility = View.GONE
                                startActivity(Intent(this@MyItemsActivity, MyItemsActivity::class.java))
                            }
                            is Result.Error -> {
                                binding.progressBar12.visibility = View.GONE
                            }
                        }
                    }
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