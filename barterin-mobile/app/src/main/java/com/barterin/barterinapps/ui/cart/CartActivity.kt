package com.barterin.barterinapps.ui.cart

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
import com.barterin.barterinapps.databinding.ActivityCartBinding
import com.barterin.barterinapps.ui.bottomnavigation.ui.home.HomeViewModel
import com.barterin.barterinapps.viewmodel.ViewModelFactory

class CartActivity : AppCompatActivity() {

    private var _binding: ActivityCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        getCartList()

    }

    private fun getCartList() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[CartViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)
        val cartAdapter = CartAdapter()

        viewModel.getCartItem(sharedpref.getToken()).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar10.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        val data = result.data
                        cartAdapter.setList(data)
                        binding.progressBar10.visibility = View.GONE
                    }
                    is Result.Error -> {
                        Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        with(binding.rvCart) {
            this.layoutManager = LinearLayoutManager(this@CartActivity)
            this.setHasFixedSize(true)
            this.adapter?.notifyDataSetChanged()
            this.adapter = cartAdapter
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