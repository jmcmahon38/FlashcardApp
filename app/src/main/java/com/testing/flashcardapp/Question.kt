package com.testing.flashcardapp

class Question (isChecked: String, question:String, answer:String){
    var isChecked: String = isChecked
        get() = field
        set(value: String) {
            field = value
        }

    var question: String = question
        get() = field
        set(value: String) {
            field = value
        }
    var answer: String = answer
        get() = field
        set(value: String) {
            field = value
        }
}