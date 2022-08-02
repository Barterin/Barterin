package com.barterin.barterinapps.ui.myitems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityBarterSuccesBinding
import com.barterin.barterinapps.databinding.ActivityLoginBinding
import com.barterin.barterinapps.ui.bottomnavigation.HomeActivity

class BarterSuccesActivity : AppCompatActivity() {

    private var _binding: ActivityBarterSuccesBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityBarterSuccesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@BarterSuccesActivity, HomeActivity::class.java ))
        finish()
    }
}