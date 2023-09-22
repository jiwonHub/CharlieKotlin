package com.example.charliekotlin.home.inner

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.charliekotlin.R
import com.example.charliekotlin.databinding.FragmentHomeQuestionBinding
import com.example.charliekotlin.home.easy.EasyQuestionCompilationActivity

class HomeQuestionFragment : Fragment(R.layout.fragment_home_question) {

    private var binding : FragmentHomeQuestionBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeQuestionBinding = FragmentHomeQuestionBinding.bind(view)
        binding = fragmentHomeQuestionBinding

        binding!!.easyLayout.setOnClickListener {
            val intent = Intent(context, EasyQuestionCompilationActivity::class.java)
            startActivity(intent)
        }
    }
}