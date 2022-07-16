package com.barterin.barterinapps.ui.bottomnavigation.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.FragmentProfileBinding
import com.barterin.barterinapps.ui.addresslist.AddressActivity
import com.barterin.barterinapps.ui.updateprofile.UpdateProfileActivity
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


        Glide.with(this)
            .load(R.drawable.placeholder)
            .into(binding.imgProfile)

        binding.txtAddressList.setOnClickListener {
            Intent(requireContext(), AddressActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.txtPersonalData.setOnClickListener {
            Intent(requireContext(), UpdateProfileActivity::class.java).also {
                startActivity(it)
            }
        }

        getData()

        return binding.root
    }

    private fun getData() {

        sharedpref = SharedPreferenceClass(requireContext())

        Glide.with(this)
            .load("https://api.barterin.tech/uploads/images/profiles/${sharedpref.getProfilePicture()}")
            .placeholder(R.drawable.placeholder)
            .into(binding.imgProfile)

        binding.txtName.text = sharedpref.getToken()
        binding.txtUsername.text = sharedpref.getUsername()
        binding.txtEmail.text = sharedpref.getEmail()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}