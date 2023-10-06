package com.example.charliekotlin.home.community.communitydetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.charliekotlin.DBKey
import com.example.charliekotlin.DBKey.Companion.DB_CHAT
import com.example.charliekotlin.DBKey.Companion.DB_COMMUNITY
import com.example.charliekotlin.databinding.ActivityCommunityDetailBinding
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

class CommunityDetailActivity: AppCompatActivity() {

    private lateinit var binding : ActivityCommunityDetailBinding
    private val chatList = mutableListOf<CommunityDetailData>()
    private val adapter = CommunityDetailAdapter()
    private var chatDB: DatabaseReference? = null
    private lateinit var name: String

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommunityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val uri = intent.getStringExtra("uri")
        val time = intent.getLongExtra("time", -1)
        name = intent.getStringExtra("name").toString()

        val postDate = SimpleDateFormat("MM/dd")
        val postTime = SimpleDateFormat("hh:mm")

        binding.titleTextView.text = title.toString()
        binding.contentTextView.text = content.toString()
        if (uri!!.isNotEmpty()){
            Glide.with(binding.iamgeView)
                .load(uri)
                .into(binding.iamgeView)
        }

        binding.dateTextView.text = postDate.format(time)
        binding.timeTextView.text = postTime.format(time)
        binding.profileName.text = name

        chatDB = Firebase.database.reference.child(DB_CHAT).child("chat")

        chatDB?.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatItem = snapshot.getValue(CommunityDetailData::class.java)
                chatItem ?: return

                chatList.add(chatItem) // 가져온 채팅들을 리스트(chatList)에 저장.
                adapter.submitList(chatList)
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {}

        })

        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.chatRecyclerView.adapter = adapter
        binding.chatRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding.communityDetailScrollView.requestDisallowInterceptTouchEvent(true)
            }
        })



        binding.chatButton.setOnClickListener {
            val chatTime = System.currentTimeMillis()
            val chatItem = CommunityDetailData(
                name = name,
                chat = binding.chatEditText.text.toString(),
                time = chatTime
            )
            binding.chatEditText.text = null

            chatDB?.push()?.setValue(chatItem)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}