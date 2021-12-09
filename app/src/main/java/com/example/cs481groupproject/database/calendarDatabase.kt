package com.example.cs481groupproject.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [calendarEvent::class], version = 2)
abstract class calendarDatabase : RoomDatabase() {
    abstract fun calendarInterface(): calendarInterface
}