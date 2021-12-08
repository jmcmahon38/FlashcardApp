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
    private var userInterval: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card)
        val extras = intent.extras
        if (extras != null) {

            var ques1 = extras.getString("Ques1")
            var ans1 = extras.getString("Ans1")
            var ques2 = extras.getString("Ques2")
            var ans2 = extras.getString("Ans2")

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

        }
        initViews()

    }


    // create on click listener for answer on submit


    private fun initViews() {
        tvTimer = findViewById(R.id.idPopInterval)
        btnStartStop = findViewById(R.id.btnStartStop)
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
    private var countDownTimer = object : CountDownTimer(1000 * 10, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            Log.d(TAG, "onTick: ${millisUntilFinished / 1000f}")
            // update text
            tvTimer?.text = getString(
                R.string.formatted_time,
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
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
        tvTimer?.text = "00:10"
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