package com.example.timetrackerapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetrackerapp.database.TaskEntity
import com.example.timetrackerapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository): ViewModel(){

    val tasksLiveData = taskRepository.tasksLiveData

    fun getAllTasks() {
        viewModelScope.launch {
            taskRepository.getAllTasks()
        }
    }

    fun addTask(taskEntity: TaskEntity) {
        viewModelScope.launch {
            taskRepository.addTask(taskEntity)
        }
    }

}