package com.barterin.barterinapps.ui.showbidder

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityLoginBinding
import com.barterin.barterinapps.databinding.ActivityShowBidderBinding
import com.barterin.barterinapps.ui.bottomnavigation.ui.offer.OfferViewModel
import com.barterin.barterinapps.viewmodel.ViewModelFactory

class ShowBidderActivity : AppCompatActivity() {

    private var _binding: ActivityShowBidderBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityShowBidderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        getBidderList()

    }

    private fun getBidderList() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[ShowBidderViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        val showBidderAdapter = ShowBidderAdapter()
        val id = intent.getStringExtra("id").toString()

        viewModel.showBidder(id, sharedpref.getToken()).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar16.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        val data = result.data
                        showBidderAdapter.setList(data)
                        binding.progressBar16.visibility = View.GONE
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            this,
                            result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("ocikepung", result.error)
                    }
                }
            }
        }

        binding.rvBidder.apply {
            layoutManager = LinearLayoutManager(this@ShowBidderActivity)
            setHasFixedSize(true)
            adapter?.notifyDataSetChanged()
            this.adapter = showBidderAdapter
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