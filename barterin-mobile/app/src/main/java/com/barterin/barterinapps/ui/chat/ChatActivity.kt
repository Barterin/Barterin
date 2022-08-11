package com.barterin.barterinapps.ui.chat

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityChatBinding
import com.barterin.barterinapps.ui.bottomnavigation.HomeActivity

class ChatActivity : AppCompatActivity() {


    private var _binding: ActivityChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        binding.webView.settings.javaScriptEnabled = true

        binding.webView.webViewClient = object : WebViewClient() {

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                try {
                    binding.webView.stopLoading()
                } catch (e: Exception) {
                }
                try {
                    binding.webView.clearView()
                } catch (e: Exception) {
                }
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                }
                binding.webView.loadUrl("about:blank")
                val alertDialog = AlertDialog.Builder(this@ChatActivity).create()
                alertDialog.setTitle("Gagal Membuka")
                alertDialog.setMessage(
                    """
                    Pastikan android anda terhubung ke internet lalu coba lagi !
                    
                    Info: Surat yang pernah terbuka secara online akan otomatis tersimpan dan tersedia saat offline.
                """.trimIndent()
                )
                alertDialog.setButton(
                    DialogInterface.BUTTON_POSITIVE,
                    "Ok"
                ) { _: DialogInterface, _: Int ->
                    moveBack()
                }
                alertDialog.show()
                super.onReceivedError(binding.webView, errorCode, description, failingUrl)
            }

        }

        binding.webView.webChromeClient = WebChromeClient()
        if (savedInstanceState != null) {
            binding.webView.restoreState(savedInstanceState)
        } else {
            binding.webView.loadUrl("http://home-server.inh.pw:5500/barterin-chat/views/template.html")
        }

    }

    private fun moveBack() {
        startActivity(Intent(this, HomeActivity::class.java))
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