package com.example.charliekotlin.home.community.communitydetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.charliekotlin.databinding.ItemChatBinding
import java.text.SimpleDateFormat

class CommunityDetailAdapter: ListAdapter<CommunityDetailData, CommunityDetailAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding : ItemChatBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SimpleDateFormat")
        fun bind(chatItem: CommunityDetailData){
            val postDate = SimpleDateFormat("MM/dd")
            val postTime = SimpleDateFormat("hh:mm")

            binding.chatName.text = chatItem.name
            binding.chatContent.text = chatItem.chat
            binding.chatDate.text = postDate.format(chatItem.time)
            binding.chatTime.text = postTime.format(chatItem.time)
        }
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<CommunityDetailData>(){
            override fun areItemsTheSame(oldItem: CommunityDetailData, newItem: CommunityDetailData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CommunityDetailData, newItem: CommunityDetailData): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
