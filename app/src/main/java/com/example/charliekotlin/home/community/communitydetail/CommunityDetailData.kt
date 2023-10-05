package com.example.charliekotlin.home.community.communitydetail

data class CommunityDetailData(
    val name : String,
    val chat : String,
    val time : Long
){
    constructor() : this("", "", 0)
}
