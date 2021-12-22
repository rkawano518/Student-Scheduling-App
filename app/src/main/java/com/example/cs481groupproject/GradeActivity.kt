package com.example.cs481groupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.cs481groupproject.database.gradeDatabase
import com.example.cs481groupproject.database.gradeEntity
import org.w3c.dom.Text
import java.util.*

class GradeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)

        /***********************************
         * Database
         **********************************/
        //Build database
        val db = Room.databaseBuilder(
            applicationContext,
            gradeDatabase::class.java, "database-name2.db"
        ).allowMainThreadQueries()
            .build()

        //Dao
        val gradeDao = db.gradeInterface()

        /***********************************
         * End of database
         **********************************/

        //Back button
        findViewById<Button>(R.id.backButtonGrade).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Add grade button
        findViewById<Button>(R.id.bAddGrade).setOnClickListener {
            //Get values from text boxes
            val name = findViewById<EditText>(R.id.nameTextInput).text.toString()
            val grade = findViewById<EditText>(R.id.gradeTextInput).text.toString().toDouble()

            //Create gradeEntity
            val e1 = gradeEntity(name, grade)

            //Add entity to database
            gradeDao.insertAll(e1)

            //Show message
            Toast.makeText(this, "Grade: " + e1.name + " was added.", Toast.LENGTH_SHORT).show()
        }

        //Calculate grade button
        findViewById<Button>(R.id.bCalculateGrade).setOnClickListener {
            //Variables
            var numberOfGrades: Double = 0.0 //The amount of grades there are
            var gradeTotal: Double = 0.0 //The sum of all the grades
            var gEntities = gradeDao.getAll() //List of all the grades

            for(v in gEntities) { //Add grades to total
                gradeTotal = gradeTotal + v.grade
                numberOfGrades = numberOfGrades + 1.0
            }

            //Show final grade in text box
            findViewById<TextView>(R.id.finalGradeText).setText((gradeTotal / numberOfGrades).toString())
        }
    }
}