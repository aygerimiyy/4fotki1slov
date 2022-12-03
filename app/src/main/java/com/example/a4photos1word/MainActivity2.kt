package com.example.a4photos1word

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.a4photos1word.data.Constants
import com.example.a4photos1word.data.Question
import com.example.a4photos1word.databinding.ActivityMain2Binding
import com.example.a4photos1word.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    private lateinit var  binding: ActivityMain2Binding
    private  lateinit var  question: List<Question>
    private lateinit var prefs: SharedPreferences
    private  var currentQuestionId = -1
    private var clickedImageId = -1
    private val optionLetters = mutableListOf<Button>()
    private val answerLetters = mutableListOf<TextView>()
    private val currentAnswers = mutableListOf<Pair<String, Button>>()
    private val currentOptions = mutableListOf<Char>()
    private var count = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)

        question = Constants.getQuestions()
        binding.apply {
            optionLetters.addAll(
                listOf(
                    btn1, btn2, btn3, btn4, btn5, btn6,
                    btn7, btn8, btn9, btn10, btn11, btn12
                )
            )
            answerLetters.addAll(
                listOf(
                    answer1, answer2, answer3, answer4,
                    answer5, answer6, answer7, answer8
                )
            )
        }
        optionLetters.forEach { optionTv ->
            optionTv.addTextChangedListener {
                val letter = it.toString()
                optionTv.isEnabled = letter.isNotEmpty()
            }
        }
            answerLetters.forEach { answerTv ->
                answerTv.addTextChangedListener {
                    val letter = it.toString()
                    answerTv.isEnabled = letter.isNotEmpty()

            }

        }




        currentQuestionId = prefs.getInt(Constants.Level,0)
            setQuestion()


        binding.apply {
            btn1.setOnClickListener { optionClick(it as Button) }
            btn2.setOnClickListener { optionClick(it as Button) }
            btn3.setOnClickListener { optionClick(it as Button) }
            btn4.setOnClickListener { optionClick(it as Button) }
            btn5.setOnClickListener { optionClick(it as Button) }
            btn6.setOnClickListener { optionClick(it as Button) }
            btn7.setOnClickListener { optionClick(it as Button) }
            btn8.setOnClickListener { optionClick(it as Button) }
            btn9.setOnClickListener { optionClick(it as Button) }
            btn10.setOnClickListener { optionClick(it as Button) }
            btn11.setOnClickListener { optionClick(it as Button) }
            btn12.setOnClickListener { optionClick(it as Button) }

            answer1.setOnClickListener {  answerClick(it as TextView)}
            answer2.setOnClickListener {  answerClick(it as TextView)}
            answer3.setOnClickListener {  answerClick(it as TextView)}
            answer4.setOnClickListener {  answerClick(it as TextView)}
            answer5.setOnClickListener {  answerClick(it as TextView)}
            answer6.setOnClickListener {  answerClick(it as TextView)}
            answer7.setOnClickListener {  answerClick(it as TextView)}
            answer8.setOnClickListener {  answerClick(it as TextView)}


            btnNext.setOnClickListener {
                currentQuestionId++



                if (currentQuestionId == question.size){
                    var cycle = prefs.getInt(Constants.CYCLE,0)
                    prefs.edit().putInt(Constants.CYCLE, cycle + 1).apply()
                    currentQuestionId -= question.size
                }
                setQuestion()




                if (currentQuestionId == question.size){
                    val cycle = prefs.getInt(Constants.CYCLE, 8)
                }
                setQuestion()
            }

            btnBack.setOnClickListener {
                finish()
            }



            imgPic1.setOnClickListener {
                clickedImageId = 0
                bgImage.setImageResource(question[currentQuestionId].images[0])
                bgImage.visibility = View.VISIBLE
                bgImage.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@MainActivity2,R.anim.animation_up_one
                    )
                )
            }

            ivShine.startAnimation(
                AnimationUtils.loadAnimation(
                    this@MainActivity2,R.anim.rotate
                )
            )

            imgPic2.setOnClickListener {
                clickedImageId = 1
                bgImage.setImageResource(question[currentQuestionId].images[1])
                bgImage.visibility = View.VISIBLE
                bgImage.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@MainActivity2, R.anim.animation_up_two
                    )
                )
            }


            imgPic3.setOnClickListener {
                clickedImageId = 2
                bgImage.setImageResource(question[currentQuestionId].images[2])
                bgImage.visibility = View.VISIBLE
                bgImage.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@MainActivity2, R.anim.animation_up_three
                    )
                )
            }


            imgPic4.setOnClickListener {
                clickedImageId = 3
                bgImage.setImageResource(question[currentQuestionId].images[3])
                bgImage.visibility = View.VISIBLE
                bgImage.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@MainActivity2, R.anim.animation_up_four
                    )
                )
            }




            bgImage.setOnClickListener {
                when(clickedImageId){
                    0-> {
                        bgImage.startAnimation(AnimationUtils.loadAnimation(
                            this@MainActivity2,R.anim.animation_down_one
                        ))
                        Handler().postDelayed({
                            bgImage.visibility = View.GONE
                        },200L)
                    }
                    1-> {
                        bgImage.startAnimation(
                            AnimationUtils.loadAnimation(
                                this@MainActivity2, R.anim.animation_down_two
                            )
                        )
                        Handler().postDelayed({
                            bgImage.isVisible = false
                        },200L)
                    }

                    2-> {
                        bgImage.startAnimation(
                            AnimationUtils.loadAnimation(
                                this@MainActivity2, R.anim.animation_down_three
                            )
                        )
                        Handler().postDelayed({
                            bgImage.isVisible = false
                        },200L)
                    }
                    3-> {
                        bgImage.startAnimation(
                            AnimationUtils.loadAnimation(
                                this@MainActivity2, R.anim.animation_down_four
                            )
                        )
                        Handler().postDelayed({
                            bgImage.isVisible = false
                        },200L)
                    }




                 }
                }
            }




        }



    @SuppressLint("SetTextI18n")
    private  fun setQuestion() {
        val currentQuestion = question[currentQuestionId]

        prefs.edit().putInt(Constants.Level, currentQuestionId).apply()





                binding.apply {
                    currentAnswers.clear()
                    updateAnswer(currentQuestion)
                    showContinue(false)

                    val cycle = prefs.getInt(Constants.CYCLE, 0)
                    tvLevel.text = (cycle * question.size + currentQuestionId + 1).toString()


                    imgPic1.setImageResource((currentQuestion.images[0]))
                    imgPic2.setImageResource((currentQuestion.images[1]))
                    imgPic3.setImageResource((currentQuestion.images[2]))
                    imgPic4.setImageResource((currentQuestion.images[3]))

                    val options = currentQuestion.answer.toMutableList()

                    repeat(12 - options.size) {
                        options.add(Random.nextInt(1040, 1072).toChar())
                    }
                    options.shuffle()
                    currentOptions.clear()
                    currentOptions.addAll(options)


                    options.forEachIndexed { index, letter ->
                        optionLetters[index].text = letter.toString()
                    }

                    answerLetters.forEach {
                        it.isVisible = true
                        it.isClickable = true
                    }
                    for (i in currentQuestion.answer.length until answerLetters.size) {
                        answerLetters[i].isVisible = false




        }
                }

                }

    private  fun showContinue(show: Boolean){
        binding.apply {
            bigOverplay.isVisible = show
            ivShine.isVisible = show
            btnNext.isVisible = show
            tvTextNext.isVisible = show

            ivShine.startAnimation(
                AnimationUtils.loadAnimation(
                    this@MainActivity2,R.anim.rotate
                )
            )
            if (!show){
                ivShine.clearAnimation()
            }

            answerLetters.forEach{
                it.isClickable = true
        }
    }}


    private fun optionClick(optionTv: Button){
        val currentQuestion = question[currentQuestionId]

        val index = optionLetters.indexOf(optionTv)
        val letter = currentOptions[index]


        val pairIndex = currentAnswers.indexOfFirst { it.first == "" }
        if (pairIndex == -1){
            currentAnswers.add(Pair(letter.toString(), optionTv))
        }
        else{
            currentAnswers[pairIndex] = Pair(letter.toString(), optionTv)
        }

        updateAnswer(currentQuestion)
        optionTv.text = ""


    }

    private fun answerClick(answerTv: TextView){
        answerTv.text = ""

        val index = answerLetters.indexOf(answerTv)
        val pair = currentAnswers[index]


        pair.second.text = pair.first
        currentAnswers[index] = Pair("", pair.second)
        updateAnswer(question[currentQuestionId])
    }

    private fun updateAnswer(question: Question){
        if (currentAnswers.isEmpty()){
            answerLetters.forEach {
                it.text = ""
            }
            optionLetters.forEach { option ->
                option.isClickable = true
            }
            return
        }

        currentAnswers.forEachIndexed { index, letter ->
            answerLetters[index].text = letter.first
        }
            if (question.answer.length == currentAnswers.filter { it.first.isNotEmpty()}.size) {
            if (question.answer == currentAnswers.map { it.first }.joinToString("")){
                showContinue(true)
            }
            optionLetters.forEach{option ->
                option.isClickable = false
        }
        }
        else{
                optionLetters.forEach { option ->
                    option.isClickable = true
                }
            }
    }
}
