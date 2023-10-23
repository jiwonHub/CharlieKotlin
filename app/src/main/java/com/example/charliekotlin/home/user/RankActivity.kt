package com.example.charliekotlin.home.user

import android.os.Bundle
import android.service.notification.NotificationListenerService.Ranking
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.charliekotlin.DBKey
import com.example.charliekotlin.databinding.ActivityRankBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RankActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRankBinding
    private lateinit var userName: String
    private lateinit var userId: String
    private lateinit var userImage: String

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RankAdapter
    private val rankList = mutableListOf<RankModel>()

    private lateinit var rankDB: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("kakao", MODE_PRIVATE)

        userName = sharedPreferences.getString("USER_NAME", "") ?: ""
        userId = sharedPreferences.getLong("USER_ID", 0).toString()
        userImage = sharedPreferences.getString("USER_IMAGE", "") ?: ""

        rankDB = FirebaseDatabase.getInstance().reference.child(DBKey.DB_RANK)
        recyclerView = binding.rankRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RankAdapter(rankList)
        recyclerView.adapter = adapter

        val rankValue = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                rankList.clear()
                for (childSnapShot in snapshot.children){
                    val userName = childSnapShot.child("userName").getValue(String::class.java)
                    val rankPoint = childSnapShot.child("rankPoint").getValue(Int::class.java)
                    Log.d("rank", userName.toString())
                    Log.d("rank", rankPoint.toString())

                    if (userName != null && rankPoint != null){
                        val rankItem = RankModel(userName, rankPoint)
                        rankList.add(rankItem)
                    }
                }
                rankList.sortByDescending { it.rankPoint }
                updateRankAndNotifyAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        rankDB.addValueEventListener(rankValue)
    }

    private fun updateRankAndNotifyAdapter() {
        // 랭킹 순위를 업데이트
        for ((index, rankItem) in rankList.withIndex()) {
            rankItem.rank = index + 1
        }

        // RecyclerView 업데이트
        adapter.notifyDataSetChanged()
    }
}