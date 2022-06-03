package com.barterin.barterinapps.ui.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import android.text.Spanned
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.databinding.ActivityRegisterBinding
import com.barterin.barterinapps.ui.login.LoginActivity
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import com.jakewharton.rxbinding2.widget.RxTextView


class RegisterActivity : AppCompatActivity() {

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signText.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.signupButton.setOnClickListener {

            if (binding.fullnameEditTexts.text.isNullOrEmpty()) {
                binding.txtNameEmpty.visibility = View.VISIBLE
            } else if (binding.userNameEditText.text!!.length !in 11 downTo 5) {
                binding.txtUsermaesEmpty.visibility = View.VISIBLE
            } else if (binding.emailEditText.text?.let { it1 -> Patterns.EMAIL_ADDRESS.matcher(it1).matches() } == true && binding.emailEditText.text.isNullOrEmpty()) {
                binding.txtEmailsEmpty.visibility = View.VISIBLE
            } else if (binding.passwordEditText.text!!.length < 8) {
                binding.txtPasswordsEmpty.visibility = View.VISIBLE
            } else if (!binding.checkBoxAgreement.isChecked) {
                Toast.makeText(this, "You must agree with out terms of service", Toast.LENGTH_SHORT).show()
            } else {
                registerProcess()
            }

        }

        setupView()
        formValidation()

        costumeText()

    }

    private fun costumeText() {

        val text1 = "Creating an account means you\'re okay with our Terms of Service, Privacy Policy, and our default"
        val text2 = " Notification Settings."
        val spannableStringBuilder = SpannableStringBuilder(text1)
        val spannableStringBuilder2 = SpannableStringBuilder(text2)

        val blue = ForegroundColorSpan(ContextCompat.getColor(this, R.color.light_blue))
        val blue2 = ForegroundColorSpan(ContextCompat.getColor(this, R.color.light_blue))

        spannableStringBuilder.setSpan(blue, 46, 80, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringBuilder2.setSpan(blue2, 1, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.checkBoxAgreement.text = spannableStringBuilder.append(spannableStringBuilder2)

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

    private fun registerProcess() {


        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        val username = binding.userNameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val fullName = binding.fullnameEditTexts.text.toString()
        val password = binding.passwordEditText.text.toString()

        viewModel.register(username, email, fullName, password).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar2.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar2.visibility = View.GONE
                        Toast.makeText(
                            this@RegisterActivity,
                            result.data.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is Result.Error -> {
                        binding.progressBar2.visibility = View.GONE
                        Toast.makeText(this@RegisterActivity, result.error, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun formValidation() {

        val usernameStream = binding.userNameEditText.let {
            RxTextView.textChanges(it)
                .skipInitialValue()
                .map { username ->
                    username.length !in 11 downTo 5
                }
        }

        usernameStream.subscribe {
            showUserNameAlert(it)
        }

        val emailStream = binding.emailEditText.let {
            RxTextView.textChanges(it)
                .skipInitialValue()
                .map { email ->
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                }
        }
        emailStream.subscribe {
            showEmailExistAlert(it)
        }

        val fullNameStream = binding.fullnameEditTexts.let {
            RxTextView.textChanges(it)
                .skipInitialValue()
                .map { name ->
                    name.isEmpty()
                }
        }

        fullNameStream.subscribe {
            showNameAlert(it)
        }

        val passwordStream = binding.passwordEditText.let {
            RxTextView.textChanges(it)
                .skipInitialValue()
                .map { password ->
                    password.length < 8
                }
        }

        passwordStream.subscribe {
            showPasswordMinimalAlert(it)
        }

    }

    private fun showPasswordMinimalAlert(it: Boolean?) {
        if (it == true) binding.txtPasswordsEmpty.visibility = View.VISIBLE else binding.txtPasswordsEmpty.visibility = View.INVISIBLE
    }

    private fun showNameAlert(it: Boolean?) {
        if (it == true) binding.txtNameEmpty.visibility = View.VISIBLE else binding.txtNameEmpty.visibility = View.INVISIBLE
    }

    private fun showEmailExistAlert(it: Boolean?) {
        if (it == true) binding.txtEmailsEmpty.visibility = View.VISIBLE else binding.txtEmailsEmpty.visibility = View.INVISIBLE
    }

    private fun showUserNameAlert(it: Boolean?) {
        if (it == true) binding.txtUsermaesEmpty.visibility = View.VISIBLE else binding.txtUsermaesEmpty.visibility = View.INVISIBLE
    }

}