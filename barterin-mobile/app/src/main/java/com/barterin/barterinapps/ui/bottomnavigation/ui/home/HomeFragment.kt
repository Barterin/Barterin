package com.barterin.barterinapps.ui.bottomnavigation.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.FragmentHomeBinding
import com.barterin.barterinapps.ui.addresslist.AddressAdapter
import com.barterin.barterinapps.ui.addresslist.AddressViewModel
import com.barterin.barterinapps.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            getCategoryList()
            getBarterList()
        } else {
            Toast.makeText(requireContext(), "yahaha", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getBarterList() {
        Toast.makeText(requireContext(), "barter loading", Toast.LENGTH_SHORT).show()
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        sharedpref = SharedPreferenceClass(requireContext())
        val barteritemsAdapter = BarterItemsAdapter()
        viewModel.getAllBarterItems().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Success -> {
                        val data = result.data
                        barteritemsAdapter.setList(data)
                        Toast.makeText(requireContext(), "barter berhasil", Toast.LENGTH_SHORT).show()
                        Log.d("myresult", result.data.toString())
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "error nih" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        with(binding.rvBarteritem) {
            this.layoutManager = GridLayoutManager(context, 2)
            this.setHasFixedSize(true)
            this.adapter?.notifyDataSetChanged()
            this.adapter = barteritemsAdapter
        }
    }

    private fun getCategoryList() {

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        sharedpref = SharedPreferenceClass(requireContext())
        val categoryAdapter = CatgoryAdapter()
        viewModel.getCategoryList(sharedpref.getToken()).observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                    }
                    is Result.Success -> {
                        val data = result.data
                        categoryAdapter.setList(data)
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "error nih" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        with(binding.rvCategory) {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.setHasFixedSize(true)
            this.adapter?.notifyDataSetChanged()
            this.adapter = categoryAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}