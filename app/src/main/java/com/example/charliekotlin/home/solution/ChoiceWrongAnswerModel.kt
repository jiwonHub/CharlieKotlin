package com.example.charliekotlin.home.solution

data class ChoiceWrongAnswerModel(
    val id: String,
    val name: String,
    val title: String,
    val content: String,
    val number: String,
    val difficulty: String,
    val explan: String,
    val limit: String,
    val select: String,
    val correct: String,
    val choice1: String,
    val choice2: String,
    val choice3: String,
    val choice4: String,
    val choice5: String,
){
    constructor() : this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
}
