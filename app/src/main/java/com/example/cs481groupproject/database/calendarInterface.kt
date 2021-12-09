package com.example.cs481groupproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface calendarInterface {
    //Gets all events
    @Query("SELECT * FROM calendarEvent")
    fun getAll(): List<calendarEvent>

    //Insert
    @Insert
    fun insertAll(vararg events: calendarEvent)
}