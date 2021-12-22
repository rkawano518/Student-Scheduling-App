package com.example.cs481groupproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface gradeInterface {//Gets all events
    @Query("SELECT * FROM gradeEntity")
    fun getAll(): List<gradeEntity>

    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg events: gradeEntity)
}