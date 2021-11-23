package com.testing.flashcardapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class TableLayout : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_layout)

        //use list of Question objects to call on flashcard screen
        var questionList = mutableListOf<Question>()

        val startButton = findViewById<Button>(R.id.idBtnStart)
    }
    // change screen on start -- this will go to the 3rd screen
    //  but testing use of passing data here for now
    fun onStartBtnClick(view: android.view.View) {
        val question1 = findViewById<EditText>(R.id.idQues1).text.toString()
        val answer1 = findViewById<EditText>(R.id.idAns1).text.toString()

        //start intent for new Activity and pass data
        val intent = Intent(this@TableLayout, MainActivity::class.java)
        intent.putExtra("Question", question1)
        intent.putExtra("Answer", answer1)
        startActivity(intent)
    }
}


