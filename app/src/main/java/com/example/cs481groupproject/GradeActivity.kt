package com.example.cs481groupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.room.Room
import com.example.cs481groupproject.database.calendarDatabase
import com.example.cs481groupproject.database.calendarEvent
import java.text.SimpleDateFormat
import java.util.*

class GradeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)

        //Back button
        findViewById<Button>(R.id.button3).setOnClickListener {

        }
        findViewById<Button>(R.id.backButtonGrade).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button5).setOnClickListener {


            class Student(
                val name: String,
                val gpa: Double,
                val semester: String,
                val estimatedGraduationYear: Int
            ) {

                init {
                    println("$name has ${estimatedGraduationYear - 2020} years left in college.")
                }

                // Member Function
                fun calculateLetterGrade(): String {
                    return when {
                        gpa >= 3.0 -> "A"
                        gpa >= 2.7 -> "B"
                        gpa >= 1.7 -> "C"
                        gpa >= 1.0 -> "D"
                        else -> "E"
                    }
                }
            }

        fun main() {
            var student =
                Student("Alex", 3.95, "Fall", 2022) // Prints: Alex has 0 years left in college.
            println("${student.name}'s letter grade is ${student.calculateLetterGrade()}.") // Prints: Alex's letter grade is A.
        }}}}