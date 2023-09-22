package com.example.charliekotlin.home.easy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.charliekotlin.databinding.ActivityEasyQuestionCompilationBinding
import com.example.charliekotlin.databinding.ItemEasyQuestionBinding

class EasyQuestionCompilationAdapter(val onItemClicked: (EasyModel) -> Unit) :
    ListAdapter<EasyModel,EasyQuestionCompilationAdapter.ViewHolder>(diffUtil){

    private var currentList: List<EasyModel> = emptyList()

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<EasyModel>(){
            override fun areItemsTheSame(oldItem: EasyModel, newItem: EasyModel): Boolean {
                return oldItem.number == newItem.number
            }

            override fun areContentsTheSame(oldItem: EasyModel, newItem: EasyModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEasyQuestionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }
    inner class ViewHolder(private val binding: ItemEasyQuestionBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: EasyModel){
            binding.root.setOnClickListener {
                onItemClicked(item)
            }

            binding.easyQuestionTitle.text = item.title
        }
    }

    override fun submitList(list: List<EasyModel>?) {
        currentList = list ?: emptyList() // Update the current list
        super.submitList(list)
    }

}