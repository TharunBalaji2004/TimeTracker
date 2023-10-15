package com.example.timetrackerapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.timetrackerapp.R
import com.example.timetrackerapp.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class NewTaskSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var etTaskTitle: TextInputEditText
    private lateinit var etTaskDec: TextInputEditText
    private lateinit var btnNewTask: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        etTaskTitle = binding.etTaskTitle
        etTaskDec = binding.etTaskDesc
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnAddtask.setOnClickListener {
                val taskTitle = etTaskTitle.text.toString()
                val taskDesc = etTaskDesc.text.toString()

                val validate = validateTaskFields()

                if (validate) {
                    Log.d("NEW TASK","Title: $taskTitle \n Desc: $taskDesc")
                    dismiss()
                    findNavController().navigate(R.id.action_homeFragment_to_taskFragment)
                }
            }
        }
    }

    private fun validateTaskFields(): Boolean {
        var validate = true

        if (etTaskTitle.text!!.isEmpty()) {
            binding.etTaskTitle.error = "Title cannot be empty"
            validate = false
        }
        else {
            binding.etTaskTitle.error = null
        }
        if (etTaskDec.text!!.isEmpty()) {
            binding.etTaskDesc.error = "Description cannot be empty"
            validate = false
        }
        else {
            binding.etTaskDesc.error = null
        }

        return validate
    }
}