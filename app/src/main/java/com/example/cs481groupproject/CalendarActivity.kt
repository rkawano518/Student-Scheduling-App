package com.example.cs481groupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
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

        //Display current date
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

        //Dao
        val calendarDao = db.calendarInterface()

        /***********************************
         * End of database
         **********************************/

        //Add button
        findViewById<Button>(R.id.bAddItem).setOnClickListener(){
            //Get values from text boxes
            val date = findViewById<EditText>(R.id.dateEditText).text.toString()
            val event = findViewById<EditText>(R.id.eventEditText).text.toString()

            //Create entity from data
            val e1 = calendarEvent(date, event)

            //Add entity to database
            calendarDao.insertAll(e1)
        }

        //Delete button
        findViewById<Button>(R.id.bRemoveItem).setOnClickListener(){
            //Get values from text boxes (need only event)
            val event = findViewById<EditText>(R.id.eventEditText).text.toString()

            //Delete entity from database
            calendarDao.deleteByEvent(event)
        }
        var cEvents: List<calendarEvent> =  calendarDao.getAll()

        //Calendar shows events on a selected date
        calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(
                view: CalendarView,
                year: Int,
                month: Int,
                dayOfMonth: Int
            ) {
                cEvents = calendarDao.getAll() //Get events into a list
                val selectedDate = ((month + 1).toString() + "/" + dayOfMonth.toString() + "/" + year.toString())//Selected date as a value

                //Loop through events list
                for(v in cEvents){
                    //If the date matches
                    if(v.date == selectedDate){
                        //Print event in text box and end loop
                        scheduled.setText("Items on " + selectedDate + ": " + v.event)
                        break
                    }
                    //Date doesn't match
                    else{
                        //Print message
                        scheduled.setText("No items scheduled")
                    }
                }
            }
        })
    }
}