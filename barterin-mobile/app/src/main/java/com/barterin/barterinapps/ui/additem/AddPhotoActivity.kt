package com.barterin.barterinapps.ui.additem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.barterin.barterinapps.databinding.ActivityAddItemBinding
import com.barterin.barterinapps.databinding.ActivityAddPhotoBinding

class AddPhotoActivity : AppCompatActivity() {

    private var _binding: ActivityAddPhotoBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)



    }
}