package com.example.charliekotlin.home.inner.wrong

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.charliekotlin.databinding.ItemWrongBinding
import com.example.charliekotlin.home.solution.ChoiceWrongAnswerModel

class WrongAdapter(val onItemClicked: (ChoiceWrongAnswerModel) -> Unit) :
    ListAdapter<ChoiceWrongAnswerModel, WrongAdapter.ViewHolder>(WrongAdapter.DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WrongAdapter.ViewHolder {
        val binding = ItemWrongBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WrongAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ItemWrongBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: ChoiceWrongAnswerModel){
            binding.title.text = item.title
            binding.root.setOnClickListener {
                onItemClicked(item)
            }
            binding.difficulty.text = item.difficulty
        }
    }

    companion object{
        val DiffUtil = object : DiffUtil.ItemCallback<ChoiceWrongAnswerModel>(){
            override fun areItemsTheSame(
                oldItem: ChoiceWrongAnswerModel,
                newItem: ChoiceWrongAnswerModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ChoiceWrongAnswerModel,
                newItem: ChoiceWrongAnswerModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}