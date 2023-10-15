package com.example.timetrackerapp.utils

import androidx.room.TypeConverter
import java.sql.Time

class TimeTypeConverter {

    @TypeConverter
    fun timeToLong(time : Time) : Long{
        return time.time
    }
    @TypeConverter

    fun longToTime(longTime : Long) : Time{
        return  Time(longTime)
    }
}