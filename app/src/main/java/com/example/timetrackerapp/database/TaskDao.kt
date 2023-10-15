package com.example.timetrackerapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.timetrackerapp.utils.Constants.TASK_TABLE

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM $TASK_TABLE ORDER BY taskId DESC")
    suspend fun getAllTasks(): MutableList<TaskEntity>

}