package com.example.charliekotlin.home.summary

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.charliekotlin.R
import com.example.charliekotlin.databinding.FragmentSummaryBinding

class SummaryFragment: Fragment(R.layout.fragment_summary) {

    private lateinit var binding: FragmentSummaryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentSummaryBinding = FragmentSummaryBinding.bind(view)
        binding = fragmentSummaryBinding

        val summaryEasyFragment = SummaryEasyFragment()

        replaceFragment(summaryEasyFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .apply {
                replace(R.id.summaryFragment, fragment)
                commit()
            }
    }
}