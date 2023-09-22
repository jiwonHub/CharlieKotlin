package com.example.charliekotlin.home

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.charliekotlin.R
import com.example.charliekotlin.databinding.FragmentHomeBinding
import com.example.charliekotlin.home.inner.HomeQuestionFragment
import com.example.charliekotlin.home.inner.HomeWrongFragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding : FragmentHomeBinding? = null
    private lateinit var examButton : Button
    private lateinit var wrongButton : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        val questionFragment = HomeQuestionFragment()
        val wrongFragment = HomeWrongFragment()

        replaceFragment(questionFragment)

        examButton = view.findViewById(R.id.examButton)
        wrongButton = view.findViewById(R.id.wrongButton)

        examButton.setOnClickListener {
            replaceFragment(questionFragment)
        }
        wrongButton.setOnClickListener {
            replaceFragment(wrongFragment)
        }

    }

    private fun replaceFragment(fragment: Fragment) { // 클릭한 바텀 네비게이션 종류에 따라 프래그먼트 변경
        childFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer_home, fragment)
                commit()
            }
    }
}