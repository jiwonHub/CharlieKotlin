package com.example.charliekotlin.home.inner

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.charliekotlin.R
import com.example.charliekotlin.databinding.FragmentHomeQuestionBinding
import com.example.charliekotlin.home.easy.EasyQuestionCompilationActivity
import com.example.charliekotlin.home.normal.NormalQuestionCompilationActivity

class HomeQuestionFragment : Fragment(R.layout.fragment_home_question) {

    private lateinit var binding : FragmentHomeQuestionBinding
    private lateinit var userName: String
    private lateinit var userImage: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeQuestionBinding = FragmentHomeQuestionBinding.bind(view)
        binding = fragmentHomeQuestionBinding

        val bundle = arguments
        userName = bundle?.getString("USER_NAME").orEmpty()
        userImage = bundle?.getString("USER_IMAGE").orEmpty()

        binding.easyLayout.setOnClickListener {
            val intent = Intent(context, EasyQuestionCompilationActivity::class.java)
            startActivity(intent)
        }
        binding.normalLayout.setOnClickListener {
            val intent = Intent(context, NormalQuestionCompilationActivity::class.java)
            startActivity(intent)
        }

    }
}