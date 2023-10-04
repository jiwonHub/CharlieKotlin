package com.example.charliekotlin.home.community.communitydetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.charliekotlin.databinding.ActivityCommunityDetailBinding

class CommunityDetailActivity: AppCompatActivity() {

    private lateinit var binding : ActivityCommunityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommunityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}