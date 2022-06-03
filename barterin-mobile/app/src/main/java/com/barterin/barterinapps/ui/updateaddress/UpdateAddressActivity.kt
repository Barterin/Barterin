package com.barterin.barterinapps.ui.updateaddress

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.data.remote.response.AddressResponse
import com.barterin.barterinapps.data.remote.response.AddressResult
import com.barterin.barterinapps.databinding.ActivityAddAdressBinding
import com.barterin.barterinapps.databinding.ActivityUpdateAddressBinding
import com.barterin.barterinapps.ui.addaddress.AddAddressViewModel
import com.barterin.barterinapps.ui.addresslist.AddressActivity
import com.barterin.barterinapps.viewmodel.ViewModelFactory

class UpdateAddressActivity : AppCompatActivity() {

    private var _binding: ActivityUpdateAddressBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityUpdateAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        getData()

    }

    private fun getData() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[UpdateAddressViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)
        val updateAddress = intent.getParcelableExtra<AddressResult>("dataAddress") as AddressResult

        binding.nameAddressEditTexts.setText(updateAddress.label)
        binding.receiverEditText.setText(updateAddress.penerima)
        binding.phoneNumberEditText.setText(updateAddress.nohp)
        binding.cityEditText.setText(updateAddress.kota_kecamatan)
        binding.addressEditText.setText(updateAddress.alamat_lengkap)
        binding.postcodeEditText.setText(updateAddress.kode_pos)

        binding.buttonSubmit.setOnClickListener {

            viewModel.updateAddress(
                updateAddress.id,
                sharedpref.getToken(),
                binding.nameAddressEditTexts.text.toString(),
                binding.receiverEditText.text.toString(),
                binding.phoneNumberEditText.text.toString(),
                binding.cityEditText.text.toString(),
                binding.addressEditText.text.toString(),
                binding.postcodeEditText.text.toString()
            ).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar4.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar4.visibility = View.GONE
                            Toast.makeText(
                                this,
                                "Address Updated",
                                Toast.LENGTH_SHORT
                            ).show()
                            Intent(this, AddressActivity::class.java).also {
                                startActivity(it)
                                finish()
                            }
                        }
                        is Result.Error -> {
                            binding.progressBar4.visibility = View.GONE
                            Toast.makeText(
                                this,
                                "getdata error" + result.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
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