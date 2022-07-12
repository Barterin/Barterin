package com.barterin.barterinapps.ui.emailverification

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.barterin.barterinapps.R.*
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityEmailVerificationBinding
import com.barterin.barterinapps.ui.bottomnavigation.HomeActivity
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class EmailVerificationActivity : AppCompatActivity() {

    private var _binding: ActivityEmailVerificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass
    private var hasExpired: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityEmailVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
//        emailVerification()
        binding.btnVerify.setOnClickListener {
            sendVerification()
        }

        sharedpref = SharedPreferenceClass(this)

        binding.textEmailHeader.text =
            "${resources.getString(string.text_send_email)} ${sharedpref.getEmail()}"

        binding.txtResend.setOnClickListener {

            if (hasExpired) {
                hasExpired = false
                countDown()
                emailVerification()
                Snackbar.make(
                    binding.root,
                    resources.getString(string.text_code_resend),
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                Snackbar.make(
                    binding.root,
                    resources.getString(string.text_code_not_expired),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        countDown()

        val otpTextViews = arrayOf(
            binding.textOtp1,
            binding.textOtp2,
            binding.textOtp3,
            binding.textOtp4,
            binding.textOtp5,
            binding.textOtp6
        )

        for (currTextView in otpTextViews) {
            currTextView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    nextTextView().requestFocus()
                }

                override fun afterTextChanged(s: Editable) {

                }
                fun nextTextView(): TextView {
                    var i = 0
                    while (i < otpTextViews.size - 1) {
                        if (otpTextViews[i] === currTextView) return otpTextViews[i + 1]
                        i++
                    }
                    return otpTextViews[i]
                }
            })
        }
    }

    private fun countDown() {

        object : CountDownTimer(120000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                val time = millisUntilFinished / 1000
                binding.textTimeHeader.text = "${resources.getString(string.text_expired)} $time"
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                binding.textTimeHeader.text = "${resources.getString(string.text_has_expired)}"
                hasExpired = true
            }
        }.start()

    }

    private fun sendVerification() {

        val verifyCode =
            "${binding.textOtp1.text}${binding.textOtp2.text}${binding.textOtp3.text}${binding.textOtp4.text}${binding.textOtp5.text}${binding.textOtp6.text}"
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[EmailVerificationViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        viewModel.sendVerification(sharedpref.getToken(), verifyCode).observe(this) { result ->
            if (result != null) {
                when (result) {
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
                    is Result.Error -> {
                        binding.progressBar3.visibility = View.GONE
                        Toast.makeText(
                            this,
                            resources.getString(string.text_error) + result.error,
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
                when (result) {
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
                            resources.getString(string.text_error) + result.error,
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