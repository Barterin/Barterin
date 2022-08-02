package com.barterin.barterinapps.ui.bottomnavigation.ui.offer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.FragmentWishlistBinding
import com.barterin.barterinapps.ui.bottomnavigation.ui.home.HomeViewModel
import com.barterin.barterinapps.viewmodel.ViewModelFactory


class OfferFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWishlistBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            getOfferList()
        }

    }

    private fun getOfferList() {

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[OfferViewModel::class.java]
        sharedpref = SharedPreferenceClass(requireContext())

        val offerItemsAdapter = OfferAdapter()

        viewModel.getOfferItem(sharedpref.getToken()).observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar13.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        val data = result.data
                        offerItemsAdapter.setList(data)
                        binding.progressBar13.visibility = View.GONE
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            requireContext(),
                            result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        with(binding.rvOffer) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter?.notifyDataSetChanged()
            this.adapter = offerItemsAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}