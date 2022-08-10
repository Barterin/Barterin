package com.barterin.barterinapps.ui.report

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityLoginBinding
import com.barterin.barterinapps.databinding.ActivityReportBinding

class ReportActivity : AppCompatActivity() {

    private var _binding: ActivityReportBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        binding.btnReport.setOnClickListener {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "message/rfc822"
            i.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("barterin.tech@gmail.com"))
            i.putExtra(Intent.EXTRA_SUBJECT, "Laporan")
            i.putExtra(Intent.EXTRA_TEXT, " ")
            try
            {
                startActivity(Intent.createChooser(i, "Send mail..."))
            }
            catch (ex:android.content.ActivityNotFoundException) {
                Toast.makeText(this@ReportActivity, "Tidak ada aplikasi email terinstall", Toast.LENGTH_SHORT).show()
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