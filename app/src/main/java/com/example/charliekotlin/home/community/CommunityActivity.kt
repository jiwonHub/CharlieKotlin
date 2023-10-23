package com.example.charliekotlin.home.community

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charliekotlin.DBKey
import com.example.charliekotlin.MainActivity
import com.example.charliekotlin.databinding.ActivityCommunityBinding
import com.example.charliekotlin.home.community.communitydetail.CommunityDetailActivity
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CommunityActivity: AppCompatActivity() {

    private lateinit var binding : ActivityCommunityBinding
    private lateinit var communityDB : DatabaseReference
    private lateinit var communityAdapter : CommunityAdapter
    private val communityList = mutableListOf<CommunityData>()
    private lateinit var userName: String
    private lateinit var userId: String
    private lateinit var userImage: String

    private val listener = object : ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val communityModel = snapshot.getValue(CommunityData::class.java)
            communityModel ?: return

            communityList.add(communityModel)
            communityAdapter.submitList(communityList)
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("kakao", MODE_PRIVATE)

        userName = sharedPreferences.getString("USER_NAME", "") ?: ""
        userId = sharedPreferences.getLong("USER_ID", 0).toString()
        userImage = sharedPreferences.getString("USER_IMAGE", "") ?: ""

        communityList.clear()
        communityDB = Firebase.database.reference.child(DBKey.DB_COMMUNITY)
        communityAdapter = CommunityAdapter(onItemClicked = { communityData ->
            val intent = Intent(this, CommunityDetailActivity::class.java)
            intent.putExtra("title", communityData.title)
            intent.putExtra("content", communityData.content)
            intent.putExtra("uri", communityData.uri)
            intent.putExtra("time", communityData.time)
            intent.putExtra("name", communityData.name)
            startActivity(intent)
        })

        communityDB.addChildEventListener(listener)

        binding.createFloatButton.setOnClickListener {
            val intent = Intent(this, CreateCommunityActivity::class.java)
            startActivity(intent)
        }
        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.communityRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.communityRecyclerView.adapter = communityAdapter
    }

    override fun onDestroy() {
        super.onDestroy()

        communityDB.removeEventListener(listener)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()

        communityAdapter.notifyDataSetChanged()
    }
}