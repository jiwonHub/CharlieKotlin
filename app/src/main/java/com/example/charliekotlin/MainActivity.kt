package com.example.charliekotlin

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.charliekotlin.calendar.CalendarFragment
import com.example.charliekotlin.databinding.ActivityMainBinding
import com.example.charliekotlin.home.HomeFragment
import com.example.charliekotlin.home.summary.SummaryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var userName: String
    private lateinit var userImage: String
    private var userId: Long = 0
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val calendarFragment = CalendarFragment()
        val summaryFragment = SummaryFragment()

        val sharedPreferences = getSharedPreferences("kakao", MODE_PRIVATE)

        userName = sharedPreferences.getString("USER_NAME", "") ?: ""
        userId = sharedPreferences.getLong("USER_ID", 0)
        userImage = sharedPreferences.getString("USER_IMAGE", "") ?: ""

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        replaceFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> replaceFragment(homeFragment)
                R.id.navigation_calendar -> replaceFragment(calendarFragment)
                R.id.navidation_study -> replaceFragment(summaryFragment)
            }
            true
        }

//      getAppKeyHash()
    }

    private fun replaceFragment(fragment: Fragment) { // 클릭한 바텀 네비게이션 종류에 따라 프래그먼트 변경
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }
    }

//    해시 키 필요할 때 해제
//    private fun getAppKeyHash() {
//        try {
//            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
//            for (signature in info.signatures) {
//                val md: MessageDigest = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                val hashKey = String(Base64.encode(md.digest(), 0))
//                Log.e(TAG, "해시키 : $hashKey")
//            }
//        } catch (e: Exception) {
//            Log.e(TAG, "해시키를 찾을 수 없습니다 : $e")
//        }
//    }
}