package com.example.charliekotlin.home.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.charliekotlin.databinding.ActivityUserInformationBinding

class UserInformationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUserInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getStringExtra("USER_IMAGE")
        val name = intent.getStringExtra("USER_NAME")

        Glide.with(binding.kakaoImage)
            .load(image)
            .into(binding.kakaoImage)
        binding.kakaoName.text = name
    }
}