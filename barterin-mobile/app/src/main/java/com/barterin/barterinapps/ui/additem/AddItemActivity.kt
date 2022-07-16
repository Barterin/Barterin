package com.barterin.barterinapps.ui.additem

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.barterin.barterinapps.databinding.ActivityAddItemBinding
import com.barterin.barterinapps.databinding.ActivityLoginBinding

class AddItemActivity : AppCompatActivity() {

    private var _binding: ActivityAddItemBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupView()

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