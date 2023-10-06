package com.example.charliekotlin.home.solution

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.charliekotlin.MainActivity
import com.example.charliekotlin.databinding.ActivityChoiceSolutionBinding
import com.example.charliekotlin.home.easy.EasyQuestionCompilationActivity

class ChoiceSolutionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChoiceSolutionBinding
    private lateinit var userName: String
    private lateinit var userImage: String
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChoiceSolutionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val number = intent.getStringExtra("number")
        val difficulty = intent.getStringExtra("difficulty")
        val explan = intent.getStringExtra("explan")
        val limit = intent.getStringExtra("limit")
        val content = intent.getStringExtra("content")
        val choice1 = intent.getStringExtra("choice1")
        val choice2 = intent.getStringExtra("choice2")
        val choice3 = intent.getStringExtra("choice3")
        val choice4 = intent.getStringExtra("choice4")
        val choice5 = intent.getStringExtra("choice5")
        val correct = intent.getStringExtra("correct")
        userName = intent.getStringExtra("userName").toString()
        userImage = intent.getStringExtra("userImage").toString()
        userId = intent.getStringExtra("userId").toString()

        binding.questionNumber.text = number
        binding.SolutionTitle.text = title
        binding.SolutionDifficulty.text = difficulty
        binding.explanationTextView.text = explan
        binding.LimitTextView.text = limit
        binding.ContentTitle.text = content
        binding.choice1.text = choice1
        binding.choice2.text = choice2
        binding.choice3.text = choice3
        binding.choice4.text = choice4
        binding.choice5.text = choice5

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.choice1.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            if (correct == "choice1") {
                intent.putExtra("resultTitle", "정답입니다!!")
                intent.putExtra("animation", "y")
                intent.putExtra("number", number)
                intent.putExtra("title", title)
                intent.putExtra("difficulty", difficulty)
                intent.putExtra("explan", explan)
                intent.putExtra("limit", limit)
                intent.putExtra("correct", correct)
                intent.putExtra("content", content)
                intent.putExtra("choice1", choice1)
                intent.putExtra("choice2", choice2)
                intent.putExtra("choice3", choice3)
                intent.putExtra("choice4", choice4)
                intent.putExtra("choice5", choice5)
                intent.putExtra("userName", userName)
                intent.putExtra("userImage", userImage)
                intent.putExtra("userId", userId)
                intent.putExtra("select", "1")
                startActivity(intent)
            } else {
                intent.putExtra("resultTitle", "틀렸습니다 ㅜㅜ")
                intent.putExtra("animation", "n")
                intent.putExtra("number", number)
                intent.putExtra("title", title)
                intent.putExtra("difficulty", difficulty)
                intent.putExtra("explan", explan)
                intent.putExtra("limit", limit)
                intent.putExtra("content", content)
                intent.putExtra("choice1", choice1)
                intent.putExtra("choice2", choice2)
                intent.putExtra("correct", correct)
                intent.putExtra("choice3", choice3)
                intent.putExtra("choice4", choice4)
                intent.putExtra("choice5", choice5)
                intent.putExtra("userName", userName)
                intent.putExtra("userImage", userImage)
                intent.putExtra("userId", userId)
                intent.putExtra("select", "1")
                startActivity(intent)
            }
        }
        binding.choice2.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            if (correct == "choice2") {
                intent.putExtra("resultTitle", "정답입니다!!")
                intent.putExtra("animation", "y")
                intent.putExtra("number", number)
                intent.putExtra("title", title)
                intent.putExtra("difficulty", difficulty)
                intent.putExtra("correct", correct)
                intent.putExtra("explan", explan)
                intent.putExtra("limit", limit)
                intent.putExtra("content", content)
                intent.putExtra("choice1", choice1)
                intent.putExtra("choice2", choice2)
                intent.putExtra("choice3", choice3)
                intent.putExtra("choice4", choice4)
                intent.putExtra("choice5", choice5)
                intent.putExtra("userName", userName)
                intent.putExtra("userImage", userImage)
                intent.putExtra("userId", userId)
                intent.putExtra("select", "2")
                startActivity(intent)
            } else {
                intent.putExtra("resultTitle", "틀렸습니다 ㅜㅜ")
                intent.putExtra("animation", "n")
                intent.putExtra("number", number)
                intent.putExtra("title", title)
                intent.putExtra("difficulty", difficulty)
                intent.putExtra("explan", explan)
                intent.putExtra("limit", limit)
                intent.putExtra("content", content)
                intent.putExtra("correct", correct)
                intent.putExtra("choice1", choice1)
                intent.putExtra("choice2", choice2)
                intent.putExtra("choice3", choice3)
                intent.putExtra("choice4", choice4)
                intent.putExtra("choice5", choice5)
                intent.putExtra("userName", userName)
                intent.putExtra("userImage", userImage)
                intent.putExtra("userId", userId)
                intent.putExtra("select", "2")
                startActivity(intent)
            }
        }
        binding.choice3.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            if (correct == "choice3") {
                intent.putExtra("resultTitle", "정답입니다!!")
                intent.putExtra("animation", "y")
                intent.putExtra("number", number)
                intent.putExtra("title", title)
                intent.putExtra("difficulty", difficulty)
                intent.putExtra("explan", explan)
                intent.putExtra("correct", correct)
                intent.putExtra("limit", limit)
                intent.putExtra("content", content)
                intent.putExtra("choice1", choice1)
                intent.putExtra("choice2", choice2)
                intent.putExtra("choice3", choice3)
                intent.putExtra("choice4", choice4)
                intent.putExtra("choice5", choice5)
                intent.putExtra("userName", userName)
                intent.putExtra("userImage", userImage)
                intent.putExtra("userId", userId)
                intent.putExtra("select", "3")
                startActivity(intent)
            } else {
                intent.putExtra("resultTitle", "틀렸습니다 ㅜㅜ")
                intent.putExtra("animation", "n")
                intent.putExtra("number", number)
                intent.putExtra("title", title)
                intent.putExtra("difficulty", difficulty)
                intent.putExtra("explan", explan)
                intent.putExtra("limit", limit)
                intent.putExtra("content", content)
                intent.putExtra("choice1", choice1)
                intent.putExtra("choice2", choice2)
                intent.putExtra("choice3", choice3)
                intent.putExtra("choice4", choice4)
                intent.putExtra("correct", correct)
                intent.putExtra("choice5", choice5)
                intent.putExtra("userName", userName)
                intent.putExtra("userImage", userImage)
                intent.putExtra("userId", userId)
                intent.putExtra("select", "3")
                startActivity(intent)
            }
        }
        binding.choice4.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            if (correct == "choice4") {
                intent.putExtra("resultTitle", "정답입니다!!")
                intent.putExtra("animation", "y")
                intent.putExtra("number", number)
                intent.putExtra("title", title)
                intent.putExtra("difficulty", difficulty)
                intent.putExtra("explan", explan)
                intent.putExtra("correct", correct)
                intent.putExtra("limit", limit)
                intent.putExtra("content", content)
                intent.putExtra("choice1", choice1)
                intent.putExtra("choice2", choice2)
                intent.putExtra("choice3", choice3)
                intent.putExtra("choice4", choice4)
                intent.putExtra("choice5", choice5)
                intent.putExtra("userName", userName)
                intent.putExtra("userImage", userImage)
                intent.putExtra("userId", userId)
                intent.putExtra("select", "4")
                startActivity(intent)
            } else {
                intent.putExtra("resultTitle", "틀렸습니다 ㅜㅜ")
                intent.putExtra("animation", "n")
                intent.putExtra("number", number)
                intent.putExtra("title", title)
                intent.putExtra("difficulty", difficulty)
                intent.putExtra("correct", correct)
                intent.putExtra("explan", explan)
                intent.putExtra("limit", limit)
                intent.putExtra("content", content)
                intent.putExtra("choice1", choice1)
                intent.putExtra("choice2", choice2)
                intent.putExtra("choice3", choice3)
                intent.putExtra("choice4", choice4)
                intent.putExtra("choice5", choice5)
                intent.putExtra("userName", userName)
                intent.putExtra("userImage", userImage)
                intent.putExtra("userId", userId)
                intent.putExtra("select", "4")
                startActivity(intent)
            }
        }
        binding.choice5.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            if (correct == "choice5") {
                intent.putExtra("resultTitle", "정답입니다!!")
                intent.putExtra("animation", "y")
                intent.putExtra("number", number)
                intent.putExtra("title", title)
                intent.putExtra("difficulty", difficulty)
                intent.putExtra("correct", correct)
                intent.putExtra("explan", explan)
                intent.putExtra("limit", limit)
                intent.putExtra("content", content)
                intent.putExtra("choice1", choice1)
                intent.putExtra("choice2", choice2)
                intent.putExtra("choice3", choice3)
                intent.putExtra("choice4", choice4)
                intent.putExtra("choice5", choice5)
                intent.putExtra("userName", userName)
                intent.putExtra("userImage", userImage)
                intent.putExtra("userId", userId)
                intent.putExtra("select", "5")
                startActivity(intent)
            } else {
                intent.putExtra("resultTitle", "틀렸습니다 ㅜㅜ")
                intent.putExtra("animation", "n")
                intent.putExtra("number", number)
                intent.putExtra("title", title)
                intent.putExtra("difficulty", difficulty)
                intent.putExtra("explan", explan)
                intent.putExtra("limit", limit)
                intent.putExtra("content", content)
                intent.putExtra("choice1", choice1)
                intent.putExtra("correct", correct)
                intent.putExtra("choice2", choice2)
                intent.putExtra("choice3", choice3)
                intent.putExtra("choice4", choice4)
                intent.putExtra("choice5", choice5)
                intent.putExtra("userName", userName)
                intent.putExtra("userImage", userImage)
                intent.putExtra("userId", userId)
                intent.putExtra("select", "5")
                startActivity(intent)
            }
        }
    }
}