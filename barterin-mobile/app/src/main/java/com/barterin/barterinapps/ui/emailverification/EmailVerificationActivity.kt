package com.barterin.barterinapps.ui.emailverification

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
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityEmailVerificationBinding
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.ui.bottomnavigation.HomeActivity

class EmailVerificationActivity : AppCompatActivity() {

    private var _binding: ActivityEmailVerificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        _binding = ActivityEmailVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        emailVerification()
        binding.btnVerify.setOnClickListener {
            sendVerification()
        }


    }

    private fun sendVerification() {

        val verifyCode = binding.editTextCode.text.toString()
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[EmailVerificationViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        viewModel.sendVerification(sharedpref.getToken(), verifyCode).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar3.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar3.visibility = View.GONE

                        Toast.makeText(
                            this,
                            result.data.message,
                            Toast.LENGTH_SHORT
                        ).show()

                        sharedpref.verifyEmail("true")
                        Intent(this@EmailVerificationActivity, HomeActivity::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    }
                    is  Result.Error -> {
                        binding.progressBar3.visibility = View.GONE
                        Toast.makeText(
                            this,
                            resources.getString(R.string.text_error) + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }



    }

    private fun emailVerification() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[EmailVerificationViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        viewModel.emailVerification(sharedpref.getToken()).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar3.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar3.visibility = View.GONE
                        Toast.makeText(
                            this,
                            result.data.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Result.Error -> {
                        binding.progressBar3.visibility = View.GONE
                        Toast.makeText(
                            this,
                            resources.getString(R.string.text_error) + result.error,
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