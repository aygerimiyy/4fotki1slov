package com.example.a4photos1word

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a4photos1word.data.Constants
import com.example.a4photos1word.data.Question
import com.example.a4photos1word.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences
    private lateinit var question: List<Question>



    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("ChechLifeCycle", "onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        question = Constants.getQuestions()
        prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)



        binding.btnPlay.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("CheckLifecycle", "onPause")
        val questionId = prefs.getInt(Constants.Level, 0)
        setQuestions(questionId)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestions(questionId: Int){
        val currentQuestion = question[questionId]

        binding.apply {
            val cycle = prefs.getInt(Constants.CYCLE, 0)
            tvLevel.text = (cycle * question.size + questionId + 1).toString()

            ivImg1.setImageResource((currentQuestion.images[0]))
            ivImg2.setImageResource((currentQuestion.images[1]))
            ivImg3.setImageResource((currentQuestion.images[2]))
            ivImg4.setImageResource((currentQuestion.images[3]))
        }
    }
}