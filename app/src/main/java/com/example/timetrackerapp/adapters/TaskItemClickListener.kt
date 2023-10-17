package com.example.timetrackerapp.adapters

import android.view.View
import com.example.timetrackerapp.database.TaskEntity

interface TaskItemClickListener {
    fun onTaskListItemClick(view: View, task: TaskEntity)
}