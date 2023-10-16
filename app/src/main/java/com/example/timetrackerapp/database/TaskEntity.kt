package com.example.timetrackerapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.timetrackerapp.utils.Constants.TASK_TABLE
import com.example.timetrackerapp.utils.TimeTypeConverter
import java.sql.Time

@Entity(tableName = TASK_TABLE)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int = 0,
    @ColumnInfo(name = "task_title")
    val taskTitle: String,
    @ColumnInfo(name = "task_desc")
    val taskDesc: String,
    @ColumnInfo(name = "task_time")
    @TypeConverters(TimeTypeConverter::class)
    val taskTime: String
)
