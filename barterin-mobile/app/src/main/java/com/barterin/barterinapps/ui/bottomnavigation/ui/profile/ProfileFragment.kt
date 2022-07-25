package com.barterin.barterinapps.ui.bottomnavigation.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.FragmentProfileBinding
import com.barterin.barterinapps.ui.addresslist.AddressActivity
import com.barterin.barterinapps.ui.login.LoginActivity
import com.barterin.barterinapps.ui.login.LoginViewModel
import com.barterin.barterinapps.ui.settings.SettingsActivity
import com.barterin.barterinapps.ui.updateprofile.UpdateProfileActivity
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private lateinit var sharedpref: SharedPreferenceClass

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(R.drawable.placeholder)
            .into(binding.imgProfile)
//
        getData()
        binding.cardviewSettings.setOnClickListener {
            Intent(requireContext(), SettingsActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.cardviewMyaccount.setOnClickListener {
            Intent(requireContext(), UpdateProfileActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.cardviewLogout.setOnClickListener {
            logoutUser()
        }


    }

    private fun logoutUser() {

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(requireActivity(), factory)[ProfileViewModel::class.java]
        sharedpref = SharedPreferenceClass(requireContext())

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(resources.getString(R.string.text_alert))
        builder.setMessage(resources.getString(R.string.text_logout_verification))
        builder.setPositiveButton(resources.getString(R.string.text_yes)) { _, _ ->

            viewModel.userLogout(sharedpref.getToken()).observe(requireActivity()) { result ->
                if (result != null) {
                    when(result) {
                        is Result.Loading -> {
                            binding.progressBar9.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar9.visibility = View.GONE
                            requireContext().getSharedPreferences("data_user_local", 0).edit().clear().apply()
                            Intent(requireContext(), LoginActivity::class.java).also {
                                startActivity(it)
                            }

                            Toast.makeText(
                                requireContext(),
                                result.data.message,
                                Toast.LENGTH_LONG
                            ).show()

                        }
                        is Result.Error -> {
                            binding.progressBar9.visibility = View.GONE
                            Toast.makeText(requireContext(), result.error, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }

        builder.setNegativeButton(resources.getString(R.string.text_no)) { dialog, _ -> // Do nothing
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun getData() {

        sharedpref = SharedPreferenceClass(requireContext())

        Glide.with(this)
            .load("https://api.barterin.tech/uploads/images/profiles/${sharedpref.getProfilePicture()}")
            .placeholder(R.drawable.placeholder)
            .into(binding.imgProfile)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}