package com.example.charliekotlin.home.summary

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.charliekotlin.R
import com.example.charliekotlin.databinding.FragmentSummaryEasyBinding

class SummaryEasyFragment: Fragment(R.layout.fragment_summary_easy) {
    private lateinit var binding: FragmentSummaryEasyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentSummaryEasyBinding = FragmentSummaryEasyBinding.bind(view)
        binding = fragmentSummaryEasyBinding
    }
}