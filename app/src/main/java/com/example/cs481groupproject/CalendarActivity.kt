package com.example.cs481groupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.room.Room
import com.example.cs481groupproject.database.calendarDatabase
import com.example.cs481groupproject.database.calendarEvent
import com.example.cs481groupproject.database.calendarInterface
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        //Back button
        findViewById<Button>(R.id.backButtonCalendar).setOnClickListener(){
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Display date
        val dateText = findViewById<TextView>(R.id.dateText)
        val date = SimpleDateFormat("M/dd/yyyy")
        val currentDate = date.format(Date())
        dateText.append(currentDate)

        //Calendar
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val scheduled = findViewById<TextView>(R.id.textScheduled)

        /***********************************
         * Database
         **********************************/
        //Build database
        val db = Room.databaseBuilder(
            applicationContext,
            calendarDatabase::class.java, "database-name"
        )   .allowMainThreadQueries()
            .build()



        val calendarDao = db.calendarInterface()
        val c1 = calendarEvent(23, "John", "Jones")
        val c2 = calendarEvent(25, "Peter", "Griffin")
        //calendarDao.insertAll(c2, c1)
        //val cEvents: List<calendarEvent> =  calendarDao.getAll()

        //Log.d("Test1", "test1")

        //println("hello")

        calendarView.setOnDateChangeListener() { calendarView: CalendarView, i: Int, i1: Int, i2: Int ->

            scheduled.setText("dfd")
        }
    }
}