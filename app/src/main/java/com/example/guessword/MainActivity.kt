package com.example.guessword

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private val questions = arrayOf(
                        Word("Day,..g..", "Night"),
                        Word("Breakfast, Launch, D.n.e.", "Dinner"),
                        Word("Sun, M..n", "Moon"),
                        Word("Up, D...", "Down"),
                        Word("Hello, G...b.e", "Goodbye"),
                        Word("Eye,Nose, ..r", "Ear"),
                        Word("Right, ...t", "Left"),
                        Word("Car, Ship, P...n", "Plain"))
    private val hintsArray = "a b c d e f j k l m o p".split(" ")

    private var currQuestion = Word("1", "2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextQuestion()
        buttonCheck.setOnClickListener { check()}
    }


    @SuppressLint("SetTextI18n")
    fun check()
    {
        val answer = inputAnswer.editText?.editableText.toString().trim()
        var message = "Incorrect: "

        if (answer.equals(currQuestion.answer))
        {
            message = "Correct"
            var score = fieldScore.text.toString().split(":")[1].trim().toInt()
            score++
            fieldScore.text = "Score: "+score.toString()
        }
        else
        {
            message += currQuestion.answer
            fieldScore.text = "Score: 0"

        }

        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        inputAnswer.editText?.setText(" ")
        nextQuestion()
    }

    @SuppressLint("SetTextI18n")
    fun nextQuestion()
    {
        val n = Random.nextInt(until = questions.lastIndex)
        Log.println(Log.DEBUG, "Question", questions[n].question)
        Log.println(Log.DEBUG, "Random", n.toString())
        currQuestion = questions[n]

        fieldQuestion.text = "Question: " + currQuestion.question

        var hints = currQuestion.answer

        for (x in 1..3)
        {
            hints +=hintsArray[Random.nextInt(hintsArray.lastIndex)]
        }

        var s =  ""
        val chars = hints.toCharArray()
        chars.shuffle()
        for (c in chars)
        {
            s += c
            s += " "
        }

        fieldHint.text = s


    }

}