package com.barterin.barterinapps.ui.additem

import android.R
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityAddItemBinding
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import io.reactivex.internal.util.ArrayListSupplier
import java.util.ArrayList


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
                        val categoryName = arrayListOf<String?>()
                        result.data.map {
                            categoryName.add(it.name)
                        }

                        val adapter = ArrayAdapter(applicationContext, R.layout.simple_list_item_1, categoryName)
                        (binding?.categoriesNameEditText as AutoCompleteTextView).setAdapter(adapter)

                        Toast.makeText(
                            this,
                            "$categoryName",
                            Toast.LENGTH_SHORT
                        ).show()


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

}