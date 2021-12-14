package com.example.cs481groupproject.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [gradeEvent::class], version = 1)
abstract class gradeDatabase: RoomDatabase(){
    abstract fun gradeInterface():gradeInterface
}