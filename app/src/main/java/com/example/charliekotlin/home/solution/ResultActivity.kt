package com.example.charliekotlin.home.solution

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.charliekotlin.DBKey.Companion.DB_WRONG
import com.example.charliekotlin.MainActivity
import com.example.charliekotlin.Presets
import com.example.charliekotlin.databinding.ActivityResultBinding
import com.example.charliekotlin.home.community.CommunityActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ResultActivity: AppCompatActivity() {

    private lateinit var binding : ActivityResultBinding
    private lateinit var userName: String
    private lateinit var userImage: String
    private lateinit var userId: String
    private val wrongAnswerDB: DatabaseReference by lazy {
        Firebase.database.reference.child(DB_WRONG).child(userId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultTitle = intent.getStringExtra("resultTitle")
        val animation = intent.getStringExtra("animation")
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
        val select = intent.getStringExtra("select")
        userName = intent.getStringExtra("userName").toString()
        userImage = intent.getStringExtra("userImage").toString()
        userId = intent.getStringExtra("userId").toString()

        binding.difficulty.text = difficulty
        binding.resultTextView.text = resultTitle
        binding.questionTitle.text = title

        if (animation == "n"){
            binding.konfettiView.visibility = View.GONE
            saveWrongAnswer(
                userId,
                userName,
                title!!,
                content!!,
                number!!,
                difficulty!!,
                explan!!,
                limit!!,
                select!!,
                correct!!, // null
                choice1!!,
                choice2!!,
                choice3!!,
                choice4!!,
                choice5!!
            )
        }else{
            binding.konfettiView.start(Presets.parade())
        }

        binding.communityButton.setOnClickListener {
            val intent = Intent(this, CommunityActivity::class.java)
            intent.putExtra("userName", userName)
            intent.putExtra("userImage", userImage)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
        binding.homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun saveWrongAnswer(
        id: String,
        name: String,
        title: String,
        content: String,
        number: String,
        difficulty: String,
        explan: String,
        limit: String,
        select: String,
        correct: String,
        choice1: String,
        choice2: String,
        choice3: String,
        choice4: String,
        choice5: String,
    ){
        val model = ChoiceWrongAnswerModel(
            id,
            name,
            title,
            content,
            number,
            difficulty,
            explan,
            limit,
            select,
            correct,
            choice1,
            choice2,
            choice3,
            choice4,
            choice5,
        )
        wrongAnswerDB.push().setValue(model)
    }

}