package com.example.charliekotlin.home.easy.solution

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.charliekotlin.databinding.ActivityEasySolutionBinding

class EasyQuestionSolutionActivity: AppCompatActivity() {

    private lateinit var binding: ActivityEasySolutionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEasySolutionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}