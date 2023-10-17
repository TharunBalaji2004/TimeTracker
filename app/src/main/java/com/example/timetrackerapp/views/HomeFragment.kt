package com.example.timetrackerapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timetrackerapp.R
import com.example.timetrackerapp.adapters.TaskAdapter
import com.example.timetrackerapp.adapters.TaskItemClickListener
import com.example.timetrackerapp.database.TaskEntity
import com.example.timetrackerapp.databinding.FragmentHomeBinding
import com.example.timetrackerapp.utils.NetworkResult
import com.example.timetrackerapp.viewmodels.QuoteViewModel
import com.example.timetrackerapp.viewmodels.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), TaskItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val quoteViewModel by viewModels<QuoteViewModel>()
    private val taskViewModel by viewModels<TaskViewModel>()
    private lateinit var listener: TaskItemClickListener

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener = this
        taskAdapter = TaskAdapter(listener)
        setupTaskList()

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

            if (it.isEmpty()) {
                binding.tvNotasks.visibility = View.VISIBLE
            } else {
                binding.tvNotasks.visibility = View.GONE
                taskAdapter.differ.submitList(it)

            }
        }
    }

    private fun setupTaskList() {
        binding.rvTasks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }
    }

    override fun onTaskListItemClick(view: View, task: TaskEntity) {
        val bundle = Bundle()
        bundle.putInt("taskId", task.taskId)
        bundle.putString("taskTitle", task.taskTitle)
        bundle.putString("taskDesc", task.taskDesc)
        bundle.putString("taskTimer", task.taskTime.toString())

        findNavController().navigate(R.id.action_homeFragment_to_taskFragment, bundle)
    }
}