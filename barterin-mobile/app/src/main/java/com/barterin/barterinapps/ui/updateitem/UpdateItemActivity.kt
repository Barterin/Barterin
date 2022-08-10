package com.barterin.barterinapps.ui.updateitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityLoginBinding
import com.barterin.barterinapps.databinding.ActivityUpdateItemBinding

class UpdateItemActivity : AppCompatActivity() {

    private var _binding: ActivityUpdateItemBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra("id_item")
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }



}