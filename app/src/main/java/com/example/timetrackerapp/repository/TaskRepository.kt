package com.example.timetrackerapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.timetrackerapp.database.TaskDao
import com.example.timetrackerapp.database.TaskEntity
import com.example.timetrackerapp.models.QuoteResponse
import com.example.timetrackerapp.utils.NetworkResult
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    private val _tasksLiveData = MutableLiveData<MutableList<TaskEntity>>()
    val tasksLiveData: LiveData<MutableList<TaskEntity>> = _tasksLiveData

    suspend fun addTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    suspend fun getAllTasks() {
        val tasks = taskDao.getAllTasks()
        _tasksLiveData.postValue(tasks)
    }

}