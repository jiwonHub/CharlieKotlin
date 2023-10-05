package com.example.charliekotlin.home.solution

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.charliekotlin.MainActivity
import com.example.charliekotlin.databinding.ActivityResultBinding
import com.example.charliekotlin.home.community.CommunityActivity

class ResultActivity: AppCompatActivity() {

    private lateinit var binding : ActivityResultBinding

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

        binding.difficulty.text = difficulty
        binding.resultTextView.text = resultTitle
        binding.questionTitle.text = title

        if (animation == "n"){
            binding.konfettiView.visibility = View.GONE
        }else{
            binding.konfettiView.visibility = View.VISIBLE
        }

        binding.communityButton.setOnClickListener {
            val intent = Intent(this, CommunityActivity::class.java)
            startActivity(intent)
        }
        binding.homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}