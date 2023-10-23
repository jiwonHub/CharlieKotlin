package com.example.charliekotlin.home.solution

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.charliekotlin.DBKey
import com.example.charliekotlin.MainActivity
import com.example.charliekotlin.databinding.ActivityChoiceSolutionBinding
import com.example.charliekotlin.home.easy.EasyQuestionCompilationActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChoiceSolutionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChoiceSolutionBinding
    private lateinit var userName: String
    private lateinit var userImage: String
    private lateinit var userId: String
    private lateinit var number: String
    private var correctPer: Int = 0
    private var wrongPer: Int = 0
    private lateinit var percentDB: DatabaseReference

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChoiceSolutionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        number = intent.getStringExtra("number").toString()
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
        val comment = intent.getStringExtra("comment")
        val correctComment = intent.getStringExtra("correctComment")
        val sharedPreferences = getSharedPreferences("kakao", MODE_PRIVATE)

        percentDB = FirebaseDatabase.getInstance().reference.child(DBKey.DB_PER).child(number)
        percentDB.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                correctPer = snapshot.child("correctPer").getValue(Int::class.java)?:0
                wrongPer = snapshot.child("wrongPer").getValue(Int::class.java)?:0
                val totalValue = correctPer + wrongPer
                val percent: Double = if (totalValue > 0){
                    (correctPer.toDouble() / totalValue) * 100.0
                }else{
                    0.0
                }
                val formattedPercent = String.format("%.2f", percent)
                binding.answerPercentage.text = "$formattedPercent%"
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        userName = sharedPreferences.getString("USER_NAME", "") ?: ""
        userId = sharedPreferences.getLong("USER_ID", 0).toString()
        userImage = sharedPreferences.getString("USER_IMAGE", "") ?: ""

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
                intent.putExtra("comment", comment)
                intent.putExtra("correctComment", correctComment)
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
                intent.putExtra("comment", comment)
                intent.putExtra("correctComment", correctComment)
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
                intent.putExtra("comment", comment)
                intent.putExtra("correctComment", correctComment)
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
                intent.putExtra("comment", comment)
                intent.putExtra("correctComment", correctComment)
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
                intent.putExtra("comment", comment)
                intent.putExtra("correctComment", correctComment)
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
                intent.putExtra("comment", comment)
                intent.putExtra("correctComment", correctComment)
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
                intent.putExtra("comment", comment)
                intent.putExtra("correctComment", correctComment)
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
                intent.putExtra("comment", comment)
                intent.putExtra("correctComment", correctComment)
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
                intent.putExtra("comment", comment)
                intent.putExtra("correctComment", correctComment)
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
                intent.putExtra("comment", comment)
                intent.putExtra("correctComment", correctComment)
                intent.putExtra("select", "5")
                startActivity(intent)
            }
        }
    }
}