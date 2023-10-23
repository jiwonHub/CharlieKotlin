package com.example.charliekotlin.home.inner.wrong

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.charliekotlin.R
import com.example.charliekotlin.databinding.ActivityWrongDetailBinding
import com.example.charliekotlin.home.community.CommunityActivity

class WrongDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityWrongDetailBinding
    private lateinit var userId: String
    private lateinit var userName: String
    private lateinit var userImage: String

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
        val number = intent.getStringExtra("number")
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