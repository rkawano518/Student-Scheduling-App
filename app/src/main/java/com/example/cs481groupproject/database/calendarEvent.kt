package com.example.cs481groupproject.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class calendarEvent(
    @PrimaryKey val date: String, //Date of the event
    @ColumnInfo(name = "calendar_event") val event: String //Event details
)
