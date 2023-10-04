package com.example.charliekotlin.home.community

data class CommunityData(
    val title : String,
    val content : String,
    val uri : String,
    val time : Long
){
    constructor() : this("", "", "", 0)
}
