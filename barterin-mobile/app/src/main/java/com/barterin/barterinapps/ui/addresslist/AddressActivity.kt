package com.barterin.barterinapps.ui.addresslist

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
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityAddressBinding
import com.barterin.barterinapps.ui.addaddress.AddAdressActivity
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import com.barterin.barterinapps.data.Result

class AddressActivity : AppCompatActivity() {

    private var _binding: ActivityAddressBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        getList()

        binding.navAddAddress.setOnClickListener {
            Intent(this, AddAdressActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun getList() {

        val addressAdapter = AddressAdapter()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AddressViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        viewModel.getDataAddress(sharedpref.getToken()).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar5.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar5.visibility = View.GONE
                        val addressData = result.data
                        Toast.makeText(this, result.data.toString(), Toast.LENGTH_SHORT).show()
                        addressAdapter.submitList(addressData)

                    }
                    is Result.Error -> {
                        binding.progressBar5.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "error nih" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.rvAddress.apply {
            layoutManager = LinearLayoutManager(this@AddressActivity)
            setHasFixedSize(true)
            adapter = addressAdapter
        }

    }

    fun deleteAddress(id: String) {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AddressViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        viewModel.deleteAddress(sharedpref.getToken(), id).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar5.visibility = View.VISIBLE
                    }
                    is Result.Success -> {

                        binding.progressBar5.visibility = View.GONE

                        this.recreate()

                        Toast.makeText(
                            this,
                            result.data.message,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    is Result.Error -> {
                        binding.progressBar5.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "error nih" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    }

    fun updateAddress(id: String) {

        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()

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