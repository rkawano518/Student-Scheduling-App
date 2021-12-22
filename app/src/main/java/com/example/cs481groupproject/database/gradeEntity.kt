package com.example.cs481groupproject.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class gradeEntity(
    @PrimaryKey val name: String, //Name of the assignment/exam/etc
    @ColumnInfo(name = "grade") val grade: Double //Grade out of 100
)