package com.testing.flashcardapp

import android.widget.CheckBox
import android.widget.EditText

class Question(question: String, answer: String){


    var question: String? = question
        get() = field
        set(value: String?) {
            field = value
        }
    var answer: String? = answer
        get() = field
        set(value: String?) {
            field = value
        }
}