package com.example.cs481groupproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.TextView
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        //Display date
        val dateText = findViewById<TextView>(R.id.dateText)
        val date = SimpleDateFormat("M/dd/yyyy")
        val currentDate = date.format(Date())
        dateText.append(currentDate)

        //Calendar
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val scheduled = findViewById<TextView>(R.id.textScheduled)

        calendarView.setOnDateChangeListener() { calendarView: CalendarView, i: Int, i1: Int, i2: Int ->

            scheduled.setText("dfd")
        }
    }
}