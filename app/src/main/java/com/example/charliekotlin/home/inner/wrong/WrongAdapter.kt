package com.example.charliekotlin.home.inner.wrong

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.charliekotlin.DBKey
import com.example.charliekotlin.databinding.ItemWrongBinding
import com.example.charliekotlin.home.solution.ChoiceWrongAnswerModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WrongAdapter(val onItemClicked: (ChoiceWrongAnswerModel) -> Unit) :
    ListAdapter<ChoiceWrongAnswerModel, WrongAdapter.ViewHolder>(WrongAdapter.DiffUtil) {

    private lateinit var percentDB: DatabaseReference
    private var correctPer: Int = 0
    private var wrongPer: Int = 0
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

            percentDB = FirebaseDatabase.getInstance().reference.child(DBKey.DB_PER).child(item.number)
            percentDB.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    correctPer = snapshot.child("correctPer").getValue(Int::class.java)?:0
                    wrongPer = snapshot.child("wrongPer").getValue(Int::class.java)?:0
                    val totalValue = correctPer + wrongPer
                    val percent: Double = if (totalValue > 0){
                        (correctPer.toDouble() / totalValue) * 100.0
                    }else{
                        0.0
                    }
                    val formattedPercent = String.format("%.2f", percent)
                    binding.correctPer.text = "$formattedPercent%"
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
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