package com.testing.flashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnStart = findViewById<Button>(R.id.idBtnStart1) // question screen
        val timeInterval = findViewById<EditText>(R.id.idInterval)
        val btnCardEdit = findViewById<Button>(R.id.idBtnEdit)
        btnCardEdit.setOnClickListener {
            var nextScreen = Intent(this,com.testing.flashcardapp.TableLayout::class.java)
            if (intervalEntered()){
                nextScreen.putExtra("Interval", timeInterval.text.toString())
            }
            startActivity(nextScreen)
        }


        // get intent object, and data from intent
//        val intent = getIntent()
        // name = name in tableLayout
//        val question = intent.getStringExtra("Question")
//        val answer = intent.getStringExtra("Answer")



//        //form view for result
//        findViewById<TextView>(R.id.idTxt1).text = "Question: $question"
//        findViewById<TextView>(R.id.idTxt2).text = "Answer $answer"
    }

    private fun intervalEntered(): Boolean {
        val timeInterval = findViewById<EditText>(R.id.idInterval)
        if (timeInterval.text.toString().isEmpty()){
            return false
        }
        return true
    }
}
