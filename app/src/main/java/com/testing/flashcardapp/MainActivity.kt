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
        val timeInterval = findViewById<EditText>(R.id.idInterval)
        val btnCardEdit = findViewById<Button>(R.id.idBtnEdit)
        btnCardEdit.setOnClickListener {
            if (checkData()) {
                var nextScreen = Intent(this, TableLayout::class.java)
                println("${timeInterval.text}")
                nextScreen.putExtra("Interval",timeInterval.text.toString())
                startActivity(nextScreen)
            }
        }
    }

//    private fun intervalEntered(): Boolean {
//        val timeInterval = findViewById<EditText>(R.id.idInterval)
//        if (timeInterval.text.toString().isEmpty()){
//            return false
//        }
//        return true
//    }

    private fun checkData(): Boolean{
        val timeInterval = findViewById<EditText>(R.id.idInterval)
        if (timeInterval.text.toString().isEmpty()) {
            timeInterval.error = "Please enter a number value for the interval"
            return false
        }

        return true
    }
}
