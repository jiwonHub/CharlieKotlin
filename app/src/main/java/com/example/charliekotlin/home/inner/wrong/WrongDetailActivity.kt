package com.example.charliekotlin.home.inner.wrong

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.charliekotlin.DBKey
import com.example.charliekotlin.R
import com.example.charliekotlin.databinding.ActivityWrongDetailBinding
import com.example.charliekotlin.home.community.CommunityActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WrongDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityWrongDetailBinding
    private lateinit var userId: String
    private lateinit var userName: String
    private lateinit var userImage: String
    private lateinit var number: String
    private var correctPer: Int = 0
    private var wrongPer: Int = 0
    private lateinit var percentDB: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWrongDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("kakao", AppCompatActivity.MODE_PRIVATE)

        userName = sharedPreferences.getString("USER_NAME", "") ?: ""
        userId = sharedPreferences.getLong("USER_ID", 0).toString()
        userImage = sharedPreferences.getString("USER_IMAGE", "") ?: ""

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        number = intent.getStringExtra("number").toString()
        val difficulty = intent.getStringExtra("difficulty")
        val explan = intent.getStringExtra("explan")
        val limit = intent.getStringExtra("limit")
        val correct = intent.getStringExtra("correct")
        val select = intent.getStringExtra("select")
        val choice1 = intent.getStringExtra("choice1")
        val choice2 = intent.getStringExtra("choice2")
        val choice3 = intent.getStringExtra("choice3")
        val choice4 = intent.getStringExtra("choice4")
        val choice5 = intent.getStringExtra("choice5")
        val comment = intent.getStringExtra("comment")
        val correctComment = intent.getStringExtra("correctComment")

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
        binding.comment.text = comment
        binding.correctComment.text= correctComment

        percentDB = FirebaseDatabase.getInstance().reference.child(DBKey.DB_PER).child(number)
        percentDB.addListenerForSingleValueEvent(object : ValueEventListener {
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

        val correctNumber = correct!!.substring(6)
        when(select){
            "1" -> binding.choice1.setBackgroundResource(R.drawable.round_fill_red)
            "2" -> binding.choice2.setBackgroundResource(R.drawable.round_fill_red)
            "3" -> binding.choice3.setBackgroundResource(R.drawable.round_fill_red)
            "4" -> binding.choice4.setBackgroundResource(R.drawable.round_fill_red)
            "5" -> binding.choice5.setBackgroundResource(R.drawable.round_fill_red)
        }
        when(correctNumber){
            "1" -> binding.choice1.setBackgroundResource(R.drawable.round_fill_green)
            "2" -> binding.choice2.setBackgroundResource(R.drawable.round_fill_green)
            "3" -> binding.choice3.setBackgroundResource(R.drawable.round_fill_green)
            "4" -> binding.choice4.setBackgroundResource(R.drawable.round_fill_green)
            "5" -> binding.choice5.setBackgroundResource(R.drawable.round_fill_green)
        }

        binding.communityButton.setOnClickListener {
            val intent = Intent(this,CommunityActivity::class.java)
            intent.putExtra("userName", userName)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}