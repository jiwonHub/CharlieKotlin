package com.example.charliekotlin.home.easy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charliekotlin.MainActivity
import com.example.charliekotlin.databinding.ActivityEasyQuestionCompilationBinding
import com.example.charliekotlin.home.easy.retrofit.EasyDTO
import com.example.charliekotlin.home.solution.SolutionModel
import com.example.charliekotlin.home.easy.retrofit.EasyService
import com.example.charliekotlin.home.solution.ChoiceSolutionActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EasyQuestionCompilationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEasyQuestionCompilationBinding
    private lateinit var easyAdapter: EasyQuestionCompilationAdapter
    private lateinit var userName: String
    private lateinit var userImage: String
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEasyQuestionCompilationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("kakao", AppCompatActivity.MODE_PRIVATE)

        userName = sharedPreferences.getString("USER_NAME", "") ?: ""
        userId = sharedPreferences.getLong("USER_ID", 0).toString()
        userImage = sharedPreferences.getString("USER_IMAGE", "") ?: ""

        easyAdapter = EasyQuestionCompilationAdapter(onItemClicked = { easyModel ->
            when(easyModel.type){
                "choice" -> choiceActivityGo(easyModel)
            }
        })

        binding.easyCompilationRecyclerView.apply {
            adapter = easyAdapter
            layoutManager = LinearLayoutManager(context)
        }

        getQuestionList()

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun choiceActivityGo(easyModel: SolutionModel){
        val intent = Intent(this, ChoiceSolutionActivity::class.java)
        intent.putExtra("title",easyModel.title)
        intent.putExtra("number", easyModel.number)
        intent.putExtra("difficulty", easyModel.difficulty)
        intent.putExtra("explan", easyModel.explan)
        intent.putExtra("limit", easyModel.limit)
        intent.putExtra("content", easyModel.content)
        intent.putExtra("choice1", easyModel.choice1)
        intent.putExtra("choice2", easyModel.choice2)
        intent.putExtra("choice3", easyModel.choice3)
        intent.putExtra("choice4", easyModel.choice4)
        intent.putExtra("choice5", easyModel.choice5)
        intent.putExtra("correct", easyModel.correct)
        intent.putExtra("comment", easyModel.comment)
        intent.putExtra("correctComment", easyModel.correctComment)
        startActivity(intent)
    }


    private fun getQuestionList() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(EasyService::class.java).also {
            it.listQuestions()
                .enqueue(object : Callback<EasyDTO> {
                    override fun onResponse(
                        call: Call<EasyDTO>, response: Response<EasyDTO>
                    ) {
                        if (response.isSuccessful.not()) {
                            Log.d("EasyQuestionCompilationActivity", "response fail")
                            return
                        }

                        Log.d("성공", "${response.body()}")
                        response.body()?.let { DTO ->
                            easyAdapter.submitList(DTO.question)
                        }
                    }

                    override fun onFailure(call: Call<EasyDTO>, t: Throwable) {

                    }

                })
        }
    }
}