package com.example.timetrackerapp.views

import android.net.Network
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.timetrackerapp.R
import com.example.timetrackerapp.api.QuotesAPI
import com.example.timetrackerapp.databinding.FragmentHomeBinding
import com.example.timetrackerapp.utils.NetworkResult
import com.example.timetrackerapp.viewmodels.QuoteViewModel
import com.example.timetrackerapp.viewmodels.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val quoteViewModel by viewModels<QuoteViewModel>()
    private val taskViewModel by viewModels<TaskViewModel>()

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
        taskViewModel.getAllTasks()
        bindObservers()

        binding.fabAdd.setOnClickListener {
            NewTaskSheet().show(childFragmentManager, "newTaskSheet")
        }
    }

    private fun bindObservers() {
        quoteViewModel.quotesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d("QUOTES API", it.data!!.toString())
                    binding.apply {
                        tvQuotedesc.text = it.data.content
                        tvQuoteauthor.text = getString(R.string.custom_quote_author, it.data.author)
                    }
                }
                is NetworkResult.Error -> {
                    Log.e("QUOTES API", it.message.toString())
                }
                is NetworkResult.Loading -> {
                    binding.apply {
                        tvQuotedesc.text = getString(R.string.default_quote_desc)
                        tvQuoteauthor.text = getString(R.string.default_quote_author)
                    }
                }
            }
        }

        taskViewModel.tasksLiveData.observe(viewLifecycleOwner) {
            Log.d("ROOM DB", it.toString())
        }
    }
}