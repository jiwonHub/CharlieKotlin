package com.example.charliekotlin.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.charliekotlin.R
import com.example.charliekotlin.databinding.FragmentHomeBinding
import com.example.charliekotlin.home.inner.HomeQuestionFragment
import com.example.charliekotlin.home.inner.wrong.HomeWrongFragment
import com.example.charliekotlin.home.user.UserInformationActivity

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var examButton : Button
    private lateinit var wrongButton : Button

    private lateinit var userName: String
    private lateinit var userImage: String
    private lateinit var userId: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        val questionFragment = HomeQuestionFragment()
        val wrongFragment = HomeWrongFragment()

        val bundle = arguments
        userName = bundle?.getString("USER_NAME").orEmpty()
        userImage = bundle?.getString("USER_IMAGE").orEmpty()
        userId = bundle?.getString("USER_ID").orEmpty()

        replaceFragment(questionFragment)

        examButton = view.findViewById(R.id.examButton)
        wrongButton = view.findViewById(R.id.wrongButton)

        examButton.setOnClickListener {
            replaceFragment(questionFragment)
        }
        wrongButton.setOnClickListener {
            replaceFragment(wrongFragment)
        }

        binding.userButton.setOnClickListener {
            val intent = Intent(context, UserInformationActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            intent.putExtra("USER_IMAGE", userImage)
            startActivity(intent)
        }

    }

    private fun replaceFragment(fragment: Fragment) { // 클릭한 바텀 네비게이션 종류에 따라 프래그먼트 변경
        val bundle = Bundle().apply {
            putString("USER_NAME", userName)
            putString("USER_IMAGE", userImage)
            putString("USER_ID", userId)
        }
        fragment.arguments = bundle
        childFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer_home, fragment)
                commit()
            }
    }
}