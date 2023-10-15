package com.example.timetrackerapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.timetrackerapp.utils.TimeTypeConverter

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
@TypeConverters(TimeTypeConverter::class)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}