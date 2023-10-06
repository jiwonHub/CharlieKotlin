package com.example.charliekotlin.home.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.charliekotlin.databinding.ItemCommunityBinding

class CommunityAdapter(val onItemClicked: (CommunityData) -> Unit) :
    ListAdapter<CommunityData, CommunityAdapter.ViewHolder>(DiffUtil){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommunityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ItemCommunityBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: CommunityData){
            binding.communityTitle.text = item.title
            binding.communityContent.text = item.content
            binding.root.setOnClickListener {
                onItemClicked(item)
            }
            binding.communityUser.text = item.name
        }
    }

    companion object{
        val DiffUtil = object : DiffUtil.ItemCallback<CommunityData>(){
            override fun areItemsTheSame(
                oldItem: CommunityData,
                newItem: CommunityData
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CommunityData,
                newItem: CommunityData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}