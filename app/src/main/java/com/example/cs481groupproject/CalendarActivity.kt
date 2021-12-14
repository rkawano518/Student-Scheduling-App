package com.example.cs481groupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.core.view.get
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
            calendarDatabase::class.java, "database-name.db"
        )   .allowMainThreadQueries()
            .build()

        val calendarDao = db.calendarInterface()
        val c1 = calendarEvent("12/21/2021", "Homework 1")
        val c2 = calendarEvent("12/22/2021", "Exam 2")

        findViewById<Button>(R.id.bAddItem).setOnClickListener(){
            calendarDao.insertAll(c2, c1)
        }

        var cEvents: List<calendarEvent> =  calendarDao.getAll()

        findViewById<Button>(R.id.bPrintEvents).setOnClickListener(){
            println(cEvents)
            println("Date:" + calendarView.date.toString())
        }
        //println("Date:" + calendarView.date.toString())

        calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(
                view: CalendarView,
                year: Int,
                month: Int,
                dayOfMonth: Int
            ) {
                cEvents = calendarDao.getAll() //Get events
                val eventsIterator = cEvents.iterator() //Iterators
                val selectedDate = ((month + 1).toString() + "/" + dayOfMonth.toString() + "/" + year.toString())//Selected date as a value
                println("Selected Date: " + selectedDate)
                /*while(eventsIterator.hasNext()){
                    if(eventsIterator.next().date == selectedDate){
                        println("Event: " + eventsIterator.next().event)
                    }
                    //println(eventsIterator.next())
                }*/

                //Loop through events list
                for(v in cEvents){
                    //If the date matches
                    if(v.date == selectedDate){
                        //Print event in text box and end loop
                        scheduled.setText("Items on " + selectedDate + ": " + v.event)
                        break
                    }
                    //Print no events in text box
                    else{
                        scheduled.setText("No items scheduled")
                    }
                }
                //println("Date: " + (month + 1)+ "/" + dayOfMonth + "/" + year)
                //this.calendar = GregorianCalendar(year, month, dayOfMonth)
            } //met
        })

        /*calendarView.setOnDateChangeListener() { calendarView: CalendarView, i: Int, i1: Int, i2: Int ->
            println("Date #2 " + calendarView.date
            scheduled.setText(cEvents.toString())
        }*/
    }
}