package com.example.timetrackerapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.timetrackerapp.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            ivBack.setOnClickListener { findNavController().popBackStack() }
            btnPlay.setOnClickListener { timerStart() }
            btnPause.setOnClickListener { timerPause() }
            btnStop.setOnClickListener { timerStop() }

            if (arguments != null) {
                tvTasktitle.text = requireArguments().getString("taskTitle")
                tvTaskdesc.text = requireArguments().getString("taskDesc")
                tvTasktimer.text = requireArguments().getString("taskTimer")
            }
        }

    }

    private fun timerStart() {
        binding.btnPlay.visibility = View.GONE
        binding.btnPause.visibility = View.VISIBLE
        binding.btnStop.visibility = View.VISIBLE
    }

    private fun timerPause() {
        binding.btnPause.visibility = View.GONE
        binding.btnPlay.visibility = View.VISIBLE
    }

    private fun timerStop() {
        binding.btnPause.visibility = View.GONE
        binding.btnStop.visibility = View.GONE
        binding.btnPlay.visibility = View.VISIBLE
    }


}