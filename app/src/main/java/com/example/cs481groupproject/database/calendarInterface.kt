package com.example.cs481groupproject.database

import androidx.room.*

@Dao
interface calendarInterface {
    //Gets all events
    @Query("SELECT * FROM calendarEvent")
    fun getAll(): List<calendarEvent>

    //Inserts events into database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg events: calendarEvent)
    //fun insert(event: calendarEvent)

    //Delete events from database
    @Query("DELETE FROM calendarEvent WHERE calendar_event = :event")
    fun deleteByEvent(event: String)
}