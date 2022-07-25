package com.barterin.barterinapps.ui.additem

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityAddItemBinding
import com.barterin.barterinapps.ui.bottomnavigation.HomeActivity
import com.barterin.barterinapps.viewmodel.ViewModelFactory


class AddItemActivity : AppCompatActivity() {

    private var _binding: ActivityAddItemBinding? = null
    private val binding get() = _binding
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupView()

        getAutoCompleteText()

        binding?.btnNextUpload?.setOnClickListener {
            moveWithData()
        }

    }

    private fun moveWithData() {
        val intent = Intent(this, AddPhotoActivity::class.java)
//        intent.putExtra("category", binding?.categoriesNameEditText?.text.toString())
        intent.putExtra("type", binding?.itemTypeEditText?.text.toString())
        intent.putExtra("address", binding?.adressNameEditText?.text.toString())
        intent.putExtra("itemName", binding?.itemNameEditText?.text.toString())
        intent.putExtra("itemDescription", binding?.itemDescriptionEditText?.text.toString())
        intent.putExtra("usedTime", binding?.usedTimeEditText?.text.toString())
        intent.putExtra("priceRange", binding?.priceRangeEditText?.text.toString())
        startActivity(intent)
    }

    private fun getAutoCompleteText() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AddItemViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        viewModel.getCategoryList(sharedpref.getToken()).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding?.progressBar7?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding?.progressBar7?.visibility = View.GONE

                        val categoryName = arrayListOf<String>()
//                        result.data.map {
//                            categoryName.add(it.name)
//                        }


                        val adapter = ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_list_item_1,
                            categoryName
                        )

                        adapter.add(categoryName.toString().trim())
                        binding?.categoriesNameEditText?.setAdapter(adapter)

//                        val adapter = ArrayAdapter(applicationContext, R.layout.simple_list_item_1, categoryName)
//                        (binding?.categoriesNameEditText as AutoCompleteTextView).setAdapter(adapter)

                    }
                    is Result.Error -> {
                        binding?.progressBar7?.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "error nih" + result.error,
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

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

}