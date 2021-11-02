package com.example.cs481groupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Calendar button
        findViewById<Button>(R.id.bCalendar).setOnClickListener(){
            intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
    }
}