package com.testing.flashcardapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import java.io.Serializable

class TableLayout : AppCompatActivity() {
    private var chk1: CheckBox? = null
    private var chk2: CheckBox? = null
    private var chk3: CheckBox? = null
    private var chk4: CheckBox? = null
    private var chk5: CheckBox? = null
    private var chk6: CheckBox? = null
    private var chk7: CheckBox? = null
    private var chk8: CheckBox? = null
    private var chk9: CheckBox? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_layout)

        // used to determine if this question will be added
        chk1 = findViewById(R.id.idCheckBox1)
        chk2 = findViewById(R.id.idCheckBox2)
        chk3 = findViewById(R.id.idCheckBox3)
        chk4 = findViewById(R.id.idCheckBox4)
        chk5 = findViewById(R.id.idCheckBox5)
        chk6 = findViewById(R.id.idCheckBox6)
        chk7 = findViewById(R.id.idCheckBox7)
        chk8 = findViewById(R.id.idCheckBox8)
        chk9 = findViewById(R.id.idCheckBox9)

        var ques1 = findViewById<EditText>(R.id.idQues1)
        var ques2 = findViewById<EditText>(R.id.idQues2)
        var ques3 = findViewById<EditText>(R.id.idQues3)
        var ques4 = findViewById<EditText>(R.id.idQues4)
        var ques5 = findViewById<EditText>(R.id.idQues5)
        var ques6 = findViewById<EditText>(R.id.idQues6)
        var ques7 = findViewById<EditText>(R.id.idQues7)
        var ques8 = findViewById<EditText>(R.id.idQues8)
        var ques9 = findViewById<EditText>(R.id.idQues9)

        val ans1 = findViewById<EditText>(R.id.idAns1)
        val ans2 = findViewById<EditText>(R.id.idAns2)
        val ans3 = findViewById<EditText>(R.id.idAns3)
        val ans4 = findViewById<EditText>(R.id.idAns4)
        val ans5 = findViewById<EditText>(R.id.idAns5)
        val ans6 = findViewById<EditText>(R.id.idAns6)
        val ans7 = findViewById<EditText>(R.id.idAns7)
        val ans8 = findViewById<EditText>(R.id.idAns8)
        val ans9 = findViewById<EditText>(R.id.idAns9)

        val extras = intent.extras


        val startButton = findViewById<Button>(R.id.idBtnStart2)
        var backBtn = findViewById<Button>(R.id.idBtnBack)
        backBtn.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        startButton.setOnClickListener{
            val flashCardScreen = Intent(this@TableLayout, FlashCard::class.java)
            if (extras != null) {
                var interval = extras.getString("Interval")
                flashCardScreen.putExtra("Interval", interval)
            }
            if (chk1!!.isChecked) {
                flashCardScreen.putExtra("Ques1", ques1.text.toString())
                flashCardScreen.putExtra("Ans1", ans1.text.toString())
            } else{
                flashCardScreen.putExtra("Ques1", "")
                flashCardScreen.putExtra("Ans1", "")
            }
            if (chk2!!.isChecked) {
                flashCardScreen.putExtra("Ques2", ques2.text.toString())
                flashCardScreen.putExtra("Ans2", ans2.text.toString())
            } else{
                flashCardScreen.putExtra("Ques2", "")
                flashCardScreen.putExtra("Ans2", "")
            }
            startActivity(flashCardScreen)
        }

    }

}


