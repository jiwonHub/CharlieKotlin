package com.example.charliekotlin.home.easy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.charliekotlin.databinding.ItemQuestionBinding
import com.example.charliekotlin.home.solution.SolutionModel

class EasyQuestionCompilationAdapter(val onItemClicked: (SolutionModel) -> Unit) :
    ListAdapter<SolutionModel,EasyQuestionCompilationAdapter.ViewHolder>(diffUtil){

    private var currentList: List<SolutionModel> = emptyList()

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<SolutionModel>(){
            override fun areItemsTheSame(oldItem: SolutionModel, newItem: SolutionModel): Boolean {
                return oldItem.number == newItem.number
            }

            override fun areContentsTheSame(oldItem: SolutionModel, newItem: SolutionModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQuestionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }
    inner class ViewHolder(private val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: SolutionModel){
            binding.root.setOnClickListener {
                onItemClicked(item)
            }

            binding.QuestionTitle.text = item.title
        }
    }

    override fun submitList(list: List<SolutionModel>?) {
        currentList = list ?: emptyList() // Update the current list
        super.submitList(list)
    }

}