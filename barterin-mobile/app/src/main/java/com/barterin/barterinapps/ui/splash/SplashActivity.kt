package com.barterin.barterinapps.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityLoginBinding
import com.barterin.barterinapps.databinding.ActivitySplashBinding
import com.barterin.barterinapps.ui.bottomnavigation.HomeActivity
import com.barterin.barterinapps.ui.emailverification.EmailVerificationActivity
import com.barterin.barterinapps.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkStateUser()
        setupView()

        binding.btnNext.setOnClickListener {
            sharedpref = SharedPreferenceClass(this)

            if (sharedpref.getVerifiedEmail() == "true" && sharedpref.checkState()) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }

    }

    private fun checkStateUser() {
        sharedpref = SharedPreferenceClass(this)

        Handler(Looper.getMainLooper()).postDelayed({
          if (sharedpref.getVerifiedEmail() == "true" && sharedpref.checkState()) {
              startActivity(Intent(this, HomeActivity::class.java))
              finish()
          } else {
              startActivity(Intent(this, LoginActivity::class.java))
              finish()
          }
        },3000)
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