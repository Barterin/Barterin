package com.barterin.barterinapps.ui.myitems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityBarterSuccesBinding
import com.barterin.barterinapps.ui.bottomnavigation.HomeActivity
import com.bumptech.glide.Glide

class BarterSuccesActivity : AppCompatActivity() {

    private var _binding: ActivityBarterSuccesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityBarterSuccesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val status = intent.getBooleanExtra("success", false)

        if (status) {
            setupTrue()
        } else {
            setupFalse()
        }

        binding.button.setOnClickListener {
            startActivity(Intent(this@BarterSuccesActivity, HomeActivity::class.java ))
            finish()
        }
    }

    private fun setupFalse() {
        Glide.with(this)
            .load(R.drawable.ic_failed)
            .into(binding.imgStatusBarter)

        binding.txtStatusBarter.text = "Barter Failed"
        val status = intent.getStringExtra("message")
        binding.txtPesanBarter.text = status

    }

    private fun setupTrue() {
        Glide.with(this)
            .load(R.drawable.img_success)
            .into(binding.imgStatusBarter)
        binding.txtPesanBarter.text = resources.getString(R.string.text_barter_success)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@BarterSuccesActivity, HomeActivity::class.java ))
        finish()
    }

}