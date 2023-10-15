package com.example.timetrackerapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.timetrackerapp.database.TaskDao
import com.example.timetrackerapp.database.TaskDatabase
import com.example.timetrackerapp.database.TaskEntity
import com.example.timetrackerapp.utils.Constants.TASK_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(context, TaskDatabase::class.java, TASK_DB)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao()
    }

}