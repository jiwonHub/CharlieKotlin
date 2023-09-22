package com.example.charliekotlin.home.easy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charliekotlin.databinding.ActivityEasyQuestionCompilationBinding
import com.example.charliekotlin.home.easy.solution.EasyQuestionSolutionActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class EasyQuestionCompilationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEasyQuestionCompilationBinding
    private lateinit var easyAdapter: EasyQuestionCompilationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEasyQuestionCompilationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        easyAdapter = EasyQuestionCompilationAdapter(onItemClicked = { easyModel ->
             val intent = Intent(this, EasyQuestionSolutionActivity::class.java)
            startActivity(intent)
        })

        binding.easyCompilationRecyclerView.apply {
            adapter = easyAdapter
            layoutManager = LinearLayoutManager(context)
        }

        getQuestionList()
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
                        response.body()?.let { exerciseDTO ->
                            easyAdapter.submitList(exerciseDTO.question)
                        }
                    }

                    override fun onFailure(call: Call<EasyDTO>, t: Throwable) {

                    }

                })
        }
    }
}