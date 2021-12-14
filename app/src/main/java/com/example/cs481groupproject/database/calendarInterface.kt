package com.example.cs481groupproject.database

import androidx.room.*

@Dao
interface calendarInterface {
    //Gets all events
    @Query("SELECT * FROM calendarEvent")
    fun getAll(): List<calendarEvent>

    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg events: calendarEvent)
    //fun insert(event: calendarEvent)

    //Delete
    @Query("DELETE FROM calendarEvent WHERE calendar_event = :event")
    fun deleteByEvent(event: String)
}