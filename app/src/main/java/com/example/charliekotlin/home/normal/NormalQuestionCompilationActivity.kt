package com.example.charliekotlin.home.normal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charliekotlin.MainActivity
import com.example.charliekotlin.databinding.ActivityNormalQuestionCompilationBinding
import com.example.charliekotlin.home.normal.retrofit.NormalDTO
import com.example.charliekotlin.home.normal.retrofit.NormalService
import com.example.charliekotlin.home.solution.ChoiceSolutionActivity
import com.example.charliekotlin.home.solution.SolutionModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NormalQuestionCompilationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNormalQuestionCompilationBinding
    private lateinit var normalAdapter: NormalQuestionCompilationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNormalQuestionCompilationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        normalAdapter = NormalQuestionCompilationAdapter(onItemClicked = { normalModel ->
            when(normalModel.type){
                "choice" -> choiceActivityGo(normalModel)
            }
        })

        binding.normalCompilationRecyclerView.apply {
            adapter = normalAdapter
            layoutManager = LinearLayoutManager(context)
        }

        getQuestionList()

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun choiceActivityGo(normalModel: SolutionModel){
        val intent = Intent(this, ChoiceSolutionActivity::class.java)
        intent.putExtra("title",normalModel.title)
        intent.putExtra("number", normalModel.number)
        intent.putExtra("difficulty", normalModel.difficulty)
        intent.putExtra("explan", normalModel.explan)
        intent.putExtra("limit", normalModel.limit)
        intent.putExtra("content", normalModel.content)
        intent.putExtra("choice1", normalModel.choice1)
        intent.putExtra("choice2", normalModel.choice2)
        intent.putExtra("choice3", normalModel.choice3)
        intent.putExtra("choice4", normalModel.choice4)
        intent.putExtra("choice5", normalModel.choice5)
        intent.putExtra("correct", normalModel.correct)
        startActivity(intent)
    }

    private fun getQuestionList() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(NormalService::class.java).also {
            it.listQuestions()
                .enqueue(object : Callback<NormalDTO> {
                    override fun onResponse(
                        call: Call<NormalDTO>, response: Response<NormalDTO>
                    ) {
                        if (response.isSuccessful.not()) {
                            Log.d("NormalQuestionCompilationActivity", "response fail")
                            return
                        }

                        Log.d("성공", "${response.body()}")
                        response.body()?.let { DTO ->
                            normalAdapter.submitList(DTO.question)
                        }
                    }

                    override fun onFailure(call: Call<NormalDTO>, t: Throwable) {

                    }

                })
        }
    }
}