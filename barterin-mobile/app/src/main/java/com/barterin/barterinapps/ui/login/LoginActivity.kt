package com.barterin.barterinapps.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.barterin.barterinapps.R
import com.barterin.barterinapps.databinding.ActivityLoginBinding
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.ui.bottomnavigation.HomeActivity
import com.barterin.barterinapps.ui.emailverification.EmailVerificationActivity
import com.barterin.barterinapps.ui.register.RegisterActivity
import com.jakewharton.rxbinding2.widget.RxTextView

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        formValidation()

        binding.signUpText.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnLogin.setOnClickListener {
            if (binding.userNameEditText.text!!.length < 6) {
                Toast.makeText(this, "Username must not Empty !", Toast.LENGTH_SHORT).show()
            } else if (binding.passwordEditText.text!!.length < 8) {
                Toast.makeText(this, "password must not Empty !", Toast.LENGTH_SHORT).show()
            } else {
                loginProcess()
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun formValidation() {

//        val usernameStream = binding.userNameEditText.let {
//            RxTextView.textChanges(it)
//                .skipInitialValue()
//                .map { username ->
//                    username.length !in 11 downTo 5
//                }
//        }
//
//        usernameStream?.subscribe {
//            showUserNameAlert(it)
//        }
//
//        val passwordStream = binding.passwordEditText.let {
//            RxTextView.textChanges(it)
//                .skipInitialValue()
//                .map { password ->
//                    password.length < 8
//                }
//        }
//
//        passwordStream?.subscribe {
//            showPasswordMinimalAlert(it)
//        }

    }

//    private fun showPasswordMinimalAlert(it: Boolean?) {
//        if (it == true) {
//            binding.txtPasswordEmpty.visibility = View.VISIBLE
//        } else {
//            binding.txtPasswordEmpty.visibility = View.INVISIBLE
//        }
//    }
//
//    private fun showUserNameAlert(it: Boolean?) {
//        if (it == true) {
//            binding.txtUsernameEmpty.visibility = View.VISIBLE
//        } else {
//            binding.txtUsernameEmpty.visibility = View.INVISIBLE
//        }
//    }


    private fun loginProcess() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        val email = binding.userNameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        viewModel.userLogin(email, password).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE

                        val token = result.data.access.access_token
                        val tokenType = result.data.access.token_type
                        val expiresIn = result.data.access.expires_in

                        sharedpref = SharedPreferenceClass(this)
                        sharedpref.saveToken(token, tokenType, expiresIn, true)
                        getDataUser()

                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "getdata error" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    }

    private fun getDataUser() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        viewModel.getDataUser(sharedpref.getToken()).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE

                        val userId = result.data.id
                        val username = result.data.username
                        val email = result.data.email
                        val fullName = result.data.fullname
                        val verifiedEmail = result.data.verified_email
                        val verifiedAccount = result.data.verified_account
                        val phone = result.data.phone
                        val born = result.data.born
                        val profilePicture = result.data.profile_picture
                        val gender = result.data.gender

                        sharedpref = SharedPreferenceClass(this)
                        sharedpref.saveDataUser(userId, username, email, fullName, verifiedEmail, verifiedAccount, phone, born, profilePicture, gender, true)

                        if (sharedpref.checkState() && verifiedEmail == "true") {
                            Intent(this@LoginActivity, HomeActivity::class.java).also {
                                startActivity(it)
                                finish()
                            }
                        } else {
                            Intent(this@LoginActivity, EmailVerificationActivity::class.java).also {
                                startActivity(it)
                                finish()
                            }
                        }
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this,
                            resources.getString(R.string.text_error) + result.error,
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