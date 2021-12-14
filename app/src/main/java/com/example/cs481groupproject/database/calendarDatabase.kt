package com.example.cs481groupproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [calendarEvent::class], version = 2)
abstract class calendarDatabase : RoomDatabase() {
    abstract fun calendarInterface(): calendarInterface

    companion object{
        @Volatile
        private var INSTANCE: calendarDatabase? = null

        //Singleton
        fun getDatabase(context: Context): calendarDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) //Instance exists
            {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    calendarDatabase::class.java,
                    "calendar_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}