package com.example.charliekotlin.home.community

import android.net.Uri

data class CommunityData(
    val title: String,
    val content: String,
    val uri: String,
    val time: Long
){
    constructor() : this("", "", "", 0)
}
