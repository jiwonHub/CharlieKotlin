package com.example.charliekotlin.home.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.charliekotlin.databinding.ActivityUserInformationBinding
import com.example.charliekotlin.home.community.CommunityActivity

class UserInformationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUserInformationBinding
    private lateinit var userName: String
    private lateinit var userId: String
    private lateinit var userImage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("kakao", MODE_PRIVATE)

        userName = sharedPreferences.getString("USER_NAME", "") ?: ""
        userId = sharedPreferences.getLong("USER_ID", 0).toString()
        userImage = sharedPreferences.getString("USER_IMAGE", "") ?: ""

        Log.d("image", userImage)
        Log.d("name", userName)

        Glide.with(binding.kakaoImage)
            .load(userImage)
            .into(binding.kakaoImage)
        binding.kakaoName.text = userName

        binding.communityButton.setOnClickListener {
            val intent = Intent(this, CommunityActivity::class.java)
            startActivity(intent)
        }

        binding.rankButton.setOnClickListener {
            val intent = Intent(this, RankActivity::class.java)
            startActivity(intent)
        }
    }
}