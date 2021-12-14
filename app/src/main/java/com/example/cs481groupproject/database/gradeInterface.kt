package com.example.cs481groupproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface gradeInterface {//Gets all events
@Query("SELECT * FROM gradeEvent")
fun getAll(): List<gradeEvent>

    //Insert
    @Insert
    fun insertAll(vararg events: gradeEvent)
}