package com.barterin.barterinapps.ui.addaddress

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
import com.barterin.barterinapps.databinding.ActivityAddAdressBinding
import com.barterin.barterinapps.ui.addresslist.AddressActivity
import com.barterin.barterinapps.viewmodel.ViewModelFactory

class AddAdressActivity : AppCompatActivity() {

    private var _binding: ActivityAddAdressBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAddAdressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        binding.buttonSubmit.setOnClickListener {

            addAddress()

        }

    }

    private fun addAddress() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AddAddressViewModel::class.java]

        sharedpref = SharedPreferenceClass(this)

        val label = binding.nameAddressEditTexts.text.toString()
        val receiver = binding.receiverEditText.text.toString()
        val phoneNumber = binding.phoneNumberEditText.text.toString()
        val kotaKecamatan = binding.cityEditText.text.toString()
        val fullAddress = binding.addressEditText.text.toString()
        val postCode = binding.postcodeEditText.text.toString()


        viewModel.addAddress(
            sharedpref.getToken(),
            label,
            receiver,
            phoneNumber,
            kotaKecamatan,
            fullAddress,
            postCode
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
                            resources.getString(R.string.text_add_addres_success),
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
                            result.error,
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