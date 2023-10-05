package com.example.charliekotlin

import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.charliekotlin.calendar.CalendarFragment
import com.example.charliekotlin.databinding.ActivityMainBinding
import com.example.charliekotlin.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var userName: String
    private lateinit var userImage: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val calendarFragment = CalendarFragment()

        userName = intent.getStringExtra("USER_NAME") ?: ""
        userImage = intent.getStringExtra("USER_IMAGE") ?: ""

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        replaceFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> replaceFragment(homeFragment)
                R.id.navigation_calendar -> replaceFragment(calendarFragment)
            }
            true
        }

//      getAppKeyHash()
    }

    private fun replaceFragment(fragment: Fragment) { // 클릭한 바텀 네비게이션 종류에 따라 프래그먼트 변경
        val bundle = Bundle().apply {
            putString("USER_NAME", userName)
            putString("USER_IMAGE", userImage)
        }
        fragment.arguments = bundle
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