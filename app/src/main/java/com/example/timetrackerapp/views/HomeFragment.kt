package com.example.timetrackerapp.views

import android.net.Network
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.timetrackerapp.R
import com.example.timetrackerapp.api.QuotesAPI
import com.example.timetrackerapp.databinding.FragmentHomeBinding
import com.example.timetrackerapp.utils.NetworkResult
import com.example.timetrackerapp.viewmodels.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val quoteViewModel by viewModels<QuoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quoteViewModel.getQuote()

        bindObservers()
    }

    private fun bindObservers() {
        quoteViewModel.quotesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> Log.d("QUOTES API", it.data.toString())
                is NetworkResult.Error -> Log.e("QUOTES API", "Error occurred")
                is NetworkResult.Loading -> Log.d("QUOTES API", "Quote Loading")
            }
        }
    }
}