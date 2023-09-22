package com.example.charliekotlin.calendar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.charliekotlin.R
import com.example.charliekotlin.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    private var binding : FragmentCalendarBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentCalendarBinding = FragmentCalendarBinding.bind(view)
        binding = fragmentCalendarBinding
    }
}