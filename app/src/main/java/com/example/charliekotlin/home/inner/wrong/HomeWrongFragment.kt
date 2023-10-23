package com.example.charliekotlin.home.inner.wrong

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charliekotlin.DBKey
import com.example.charliekotlin.DBKey.Companion.DB_WRONG
import com.example.charliekotlin.R
import com.example.charliekotlin.databinding.FragmentHomeWrongBinding
import com.example.charliekotlin.home.community.CommunityData
import com.example.charliekotlin.home.solution.ChoiceWrongAnswerModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeWrongFragment : Fragment(R.layout.fragment_home_wrong) {

    private var binding : FragmentHomeWrongBinding? = null
    private lateinit var userId: String
    private lateinit var userName: String
    private lateinit var userImage: String
    private lateinit var wrongDB: DatabaseReference
    private val wrongList = mutableListOf<ChoiceWrongAnswerModel>()
    private lateinit var wrongAdapter : WrongAdapter

    private val listener = object : ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val wrongModel = snapshot.getValue(ChoiceWrongAnswerModel::class.java)
            wrongModel ?: return

            wrongList.add(wrongModel)
            wrongAdapter.submitList(wrongList)
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            TODO("Not yet implemented")
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeWrongBinding = FragmentHomeWrongBinding.bind(view)
        binding = fragmentHomeWrongBinding

        val sharedPreferences = requireActivity().getSharedPreferences("kakao", AppCompatActivity.MODE_PRIVATE)

        userName = sharedPreferences.getString("USER_NAME", "") ?: ""
        userId = sharedPreferences.getLong("USER_ID", 0).toString()
        userImage = sharedPreferences.getString("USER_IMAGE", "") ?: ""
        
        wrongList.clear()
        wrongDB = Firebase.database.reference.child(DB_WRONG).child(userId)

        wrongAdapter = WrongAdapter(onItemClicked = {choiceWrongAnswerModel ->  
            val intent = Intent(context, WrongDetailActivity::class.java)
            Log.d("name", choiceWrongAnswerModel.name)
            intent.putExtra("id", choiceWrongAnswerModel.id)
            intent.putExtra("name", choiceWrongAnswerModel.name)
            intent.putExtra("title", choiceWrongAnswerModel.title)
            intent.putExtra("content", choiceWrongAnswerModel.content)
            intent.putExtra("number", choiceWrongAnswerModel.number)
            intent.putExtra("difficulty", choiceWrongAnswerModel.difficulty)
            intent.putExtra("explan", choiceWrongAnswerModel.explan)
            intent.putExtra("limit", choiceWrongAnswerModel.limit)
            intent.putExtra("select", choiceWrongAnswerModel.select)
            intent.putExtra("correct", choiceWrongAnswerModel.correct)
            intent.putExtra("choice1", choiceWrongAnswerModel.choice1)
            intent.putExtra("choice2", choiceWrongAnswerModel.choice2)
            intent.putExtra("choice3", choiceWrongAnswerModel.choice3)
            intent.putExtra("choice4", choiceWrongAnswerModel.choice4)
            intent.putExtra("choice5", choiceWrongAnswerModel.choice5)
            intent.putExtra("comment", choiceWrongAnswerModel.comment)
            intent.putExtra("correctComment", choiceWrongAnswerModel.correctComment)
            startActivity(intent)
        })

        wrongDB.addChildEventListener(listener)
        binding!!.wrongRecyclerView.layoutManager = LinearLayoutManager(context)
        binding!!.wrongRecyclerView.adapter = wrongAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        wrongDB.removeEventListener(listener)
    }

    override fun onResume() {
        super.onResume()

        wrongAdapter.notifyDataSetChanged()
    }
}