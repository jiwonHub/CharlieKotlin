package com.example.charliekotlin.home.inner

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.charliekotlin.R
import com.example.charliekotlin.databinding.FragmentHomeWrongBinding

class HomeWrongFragment : Fragment(R.layout.fragment_home_wrong) {

    private var binding : FragmentHomeWrongBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeWrongBinding = FragmentHomeWrongBinding.bind(view)
        binding = fragmentHomeWrongBinding
    }
}