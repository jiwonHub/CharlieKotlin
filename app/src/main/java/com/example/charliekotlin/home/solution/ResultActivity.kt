package com.example.charliekotlin.home.solution

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.charliekotlin.DBKey.Companion.DB_PER
import com.example.charliekotlin.DBKey.Companion.DB_RANK
import com.example.charliekotlin.DBKey.Companion.DB_WRONG
import com.example.charliekotlin.MainActivity
import com.example.charliekotlin.Presets
import com.example.charliekotlin.databinding.ActivityResultBinding
import com.example.charliekotlin.home.community.CommunityActivity
import com.example.charliekotlin.home.community.CommunityAdapter
import com.example.charliekotlin.home.community.CommunityData
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ResultActivity: AppCompatActivity() {

    private lateinit var binding : ActivityResultBinding
    private lateinit var userName: String
    private lateinit var userImage: String
    private lateinit var userId: String
    private lateinit var number: String
    private val wrongAnswerDB: DatabaseReference by lazy {
        Firebase.database.reference.child(DB_WRONG).child(userId)
    }
    private lateinit var percentDB: DatabaseReference
    private lateinit var rankDB: DatabaseReference
    private var correctPer: Int = 0
    private var wrongPer: Int = 0
    private var rankPoint: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultTitle = intent.getStringExtra("resultTitle")
        val animation = intent.getStringExtra("animation")
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
        val select = intent.getStringExtra("select")
        val comment = intent.getStringExtra("comment")
        val correctComment = intent.getStringExtra("correctComment")
        val sharedPreferences = getSharedPreferences("kakao", AppCompatActivity.MODE_PRIVATE)

        userName = sharedPreferences.getString("USER_NAME", "") ?: ""
        userId = sharedPreferences.getLong("USER_ID", 0).toString()
        userImage = sharedPreferences.getString("USER_IMAGE", "") ?: ""

        percentDB = FirebaseDatabase.getInstance().reference.child(DB_PER).child(number)
        rankDB = FirebaseDatabase.getInstance().reference.child(DB_RANK).child(userId)

        percentDB.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                correctPer = snapshot.child("correctPer").getValue(Int::class.java)?:0
                wrongPer = snapshot.child("wrongPer").getValue(Int::class.java)?:0
                Log.d("correct", correctPer.toString())
                Log.d("wrong", wrongPer.toString())
                if (animation == "n"){
                    binding.konfettiView.visibility = View.GONE
                    saveWrongAnswer(
                        userId,
                        userName,
                        title!!,
                        content!!,
                        number,
                        difficulty!!,
                        explan!!,
                        limit!!,
                        select!!,
                        correct!!,
                        choice1!!,
                        choice2!!,
                        choice3!!,
                        choice4!!,
                        choice5!!,
                        comment!!,
                        correctComment!!
                    )
                    wrongPer++
                    updateWrongPerInFirebase(wrongPer)
                }else{
                    binding.konfettiView.start(Presets.parade())
                    correctPer++
                    updateCorrectPerInFirebase(correctPer)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


        rankDB.addListenerForSingleValueEvent(object : ValueEventListener{
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                rankPoint = snapshot.child("rankPoint").getValue(Int::class.java)?:1000
                if (animation == "n"){
                    when(difficulty){
                        "쉬움" -> {
                            rankPoint -= 10
                            binding.score.text = rankPoint.toString()
                            binding.scoreChange.text = "(-10)"
                        }
                        "보통" -> {
                            rankPoint -= 10
                            binding.score.text = rankPoint.toString()
                            binding.scoreChange.text = "(-10)"
                        }
                    }
                    updateRankPointInFirebase(rankPoint)
                }else{
                    when(difficulty){
                        "쉬움" -> {
                            rankPoint += 10
                            binding.score.text = rankPoint.toString()
                            binding.scoreChange.text = "(+10)"
                        }
                        "보통" -> {
                            rankPoint += 10
                            binding.score.text = rankPoint.toString()
                            binding.scoreChange.text = "(+10)"
                        }
                    }
                    updateRankPointInFirebase(rankPoint)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        Log.d("correct1", correctPer.toString())
        Log.d("wrong1", wrongPer.toString())

        binding.difficulty.text = difficulty
        binding.resultTextView.text = resultTitle
        binding.questionTitle.text = title

        binding.communityButton.setOnClickListener {
            val intent = Intent(this, CommunityActivity::class.java)
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
        comment: String,
        correctComment: String
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
            comment,
            correctComment
        )
        wrongAnswerDB.push().setValue(model)
    }

    private fun updateCorrectPerInFirebase(correctPer: Int) {
        percentDB.child("correctPer").setValue(correctPer)
    }

    private fun updateWrongPerInFirebase(wrongPer: Int) {
        percentDB.child("wrongPer").setValue(wrongPer)
    }

    private fun updateRankPointInFirebase(rankPoint: Int) {
        rankDB.child("rankPoint").setValue(rankPoint)
        rankDB.child("userName").setValue(userName)
    }

}