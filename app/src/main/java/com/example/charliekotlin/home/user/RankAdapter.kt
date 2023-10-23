package com.example.charliekotlin.home.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.charliekotlin.databinding.ItemCommunityBinding
import com.example.charliekotlin.databinding.ItemRankBinding
import com.example.charliekotlin.home.community.CommunityData

class RankAdapter(private val rankList: List<RankModel>) :
    RecyclerView.Adapter<RankAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankAdapter.ViewHolder {
        val binding = ItemRankBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RankAdapter.ViewHolder, position: Int) {
        val rankItem = rankList[position]
        holder.bind(rankItem)
    }

    override fun getItemCount() = rankList.size

    inner class ViewHolder(private val binding: ItemRankBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: RankModel){
            binding.rankName.text = item.userName
            binding.rank.text = item.rank.toString()
            binding.rankPoint.text = item.rankPoint.toString()
        }
    }
}