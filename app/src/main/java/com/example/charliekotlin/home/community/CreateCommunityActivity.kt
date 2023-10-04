package com.example.charliekotlin.home.community

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.charliekotlin.databinding.ActivityCommunityCreateBinding

class CreateCommunityActivity: AppCompatActivity() {

    private lateinit var binding : ActivityCommunityCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommunityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}