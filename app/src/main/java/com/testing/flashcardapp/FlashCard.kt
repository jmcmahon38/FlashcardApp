package com.testing.flashcardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.concurrent.TimeUnit

// credit for countdown timer: https://www.youtube.com/watch?v=eRXnvoBdPao

class FlashCard : AppCompatActivity() {
    companion object {
        const val TAG = "FlashCard"
    }
    private var tvTimer: TextView? = null // tvTimer
    private var btnSubmit: Button? = null
    private var btnStartStop: Button? = null // timer
    private var isStarted = false // timer
    private var questions = mutableListOf<String?>()
    private var answers = mutableListOf<String?>()
    var userInterval: Long = 1000 * 10 // cant get intent to transfer



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card)
        val extras = intent.extras
        if (extras != null) {

            var ques1 = extras.getString("Ques1")
            var ans1 = extras.getString("Ans1")
            var ques2 = extras.getString("Ques2")
            var ans2 = extras.getString("Ans2")
            var ques3 = extras.getString("Ques3")
            var ans3 = extras.getString("Ans3")
            var ques4 = extras.getString("Ques4")
            var ans4 = extras.getString("Ans4")
            var ques5 = extras.getString("Ques5")
            var ans5 = extras.getString("Ans5")
            var ques6 = extras.getString("Ques6")
            var ans6 = extras.getString("Ans6")
            var ques7 = extras.getString("Ques7")
            var ans7 = extras.getString("Ans7")
            var ques8 = extras.getString("Ques8")
            var ans8 = extras.getString("Ans8")
            var ques9 = extras.getString("Ques9")
            var ans9 = extras.getString("Ans9")

            if (ques1 != "") {
                questions.add(ques1)
            }
            if (ans1 != "") {
                answers.add(ans1)
            }
            if (ques2 != "") {
                questions.add(ques2)
            }
            if (ans2 != "") {
                answers.add(ans2)
            }
            if (ques3 != "") {
                questions.add(ques3)
            }
            if (ans3 != "") {
                answers.add(ans3)
            }
            if (ques4 != "") {
                questions.add(ques4)
            }
            if (ans4 != "") {
                answers.add(ans4)
            }
            if (ques5 != "") {
                questions.add(ques5)
            }
            if (ans5 != "") {
                answers.add(ans5)
            }
            if (ques6 != "") {
                questions.add(ques6)
            }
            if (ans6 != "") {
                answers.add(ans6)
            }
            if (ques7 != "") {
                questions.add(ques7)
            }
            if (ans7 != "") {
                answers.add(ans7)
            }
            if (ques8 != "") {
                questions.add(ques8)
            }
            if (ans8 != "") {
                answers.add(ans8)
            }
            if (ques9 != "") {
                questions.add(ques9)
            }
            if (ans9 != "") {
                answers.add(ans9)
            }
        }
        initViews()

    }


    // create on click listener for answer on submit


    private fun initViews() {
        tvTimer = findViewById(R.id.idPopInterval)
        btnStartStop = findViewById(R.id.txtBtnStartStop)
        var txtQues = findViewById<TextView>(R.id.idPopQuest)


        btnStartStop?.setOnClickListener{

            if(!isStarted) {
                startTimer()
                // on start, get question answer pair
                var (question, answer) = getQuestion(answers, questions)
                // publish question to question section
                txtQues.text = question.toString()
                //submit actions check answer with answer pulled from above
                submitActions(answer.toString())

            } else {
                stopTimer()
            }
        }
    }

    // need the value 30 to equal extras.getString("Interval")
    private var countDownTimer = object : CountDownTimer(userInterval, 1000) {
        override fun onTick(userInterval: Long) {
            Log.d(TAG, "onTick: ${userInterval / 1000f}")
            // update text
            tvTimer?.text = getString(
                R.string.formatted_time,
                TimeUnit.MILLISECONDS.toMinutes(userInterval) % 60,
                TimeUnit.MILLISECONDS.toSeconds(userInterval) % 60
            )
        }

        override fun onFinish() {
            var txtQues = findViewById<TextView>(R.id.idPopQuest)
            var txtAns = findViewById<EditText>(R.id.idPopAns)
            Log.d(TAG, "onFinish: called")
            isStarted = false
            btnStartStop?.text = "Start"
            startTimer()
            // on start, get question answer pair
            var (question, answer) = getQuestion(answers, questions)
            // publish question to question section
            txtQues.text = question.toString()
            txtAns.setText("")
            //submit actions check answer with answer pulled from above
            submitActions(answer.toString())

        }
    }


    private fun startTimer () {
        countDownTimer.start()
        isStarted = true
        btnStartStop?.text = "Stop"


    }

    // need text to be from extras.getString("Interval")
    private fun stopTimer() {
        var txtAns = findViewById<EditText>(R.id.idPopAns)
        countDownTimer.cancel()
        isStarted = false
        btnStartStop?.text = "Start"
        tvTimer?.text = "00:10" //can't get intent to transfer'
        txtAns.setText("")

    }
    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }

    // flashcard
    private fun getQuestion(answerList: MutableList<String?>,questionList: MutableList<String?>): Pair<String?,String?> {
        var listLength = questionList.size - 1
        var listSection = (0..listLength).random()
        var newQuestion = questionList.elementAt(listSection)
        var newAnswer = answerList.elementAt(listSection)

        return Pair(newQuestion, newAnswer)
//        return Question(newQuestion,newAnswer)
    }

    private fun submitActions(currentAnswer: String) {

        btnSubmit = findViewById(R.id.btnSubmit)
        btnSubmit?.setOnClickListener{
            var txtAns = findViewById<EditText>(R.id.idPopAns)

            if (checkAnswer(currentAnswer!!,txtAns.text.toString())) {
                val correct = "Great Job, the answer was $currentAnswer"
                txtAns.setText(correct)
//                isStarted = true
//                btnSubmit?.text = "Next"
//                stopTimer()
            } else {
                val incorrect = "Sorry the answer was $currentAnswer; getting new question..."
                txtAns.setText(incorrect)

//                isStarted = false
            }
        }

    }
    private fun checkAnswer(userAnswer:String,correctAnswer: String): Boolean {
        return userAnswer.uppercase() == correctAnswer.uppercase()
    }
}