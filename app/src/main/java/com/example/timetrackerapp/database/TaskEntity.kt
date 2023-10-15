package com.example.timetrackerapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.timetrackerapp.utils.Constants.TASK_TABLE

@Entity(tableName = TASK_TABLE)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int,
    @ColumnInfo(name = "task_title")
    val taskTitle: String,
    @ColumnInfo(name = "task_desc")
    val taskDesc: String
)
