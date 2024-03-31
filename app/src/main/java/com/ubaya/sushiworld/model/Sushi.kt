package com.ubaya.sushiworld.model

data class Sushi(
    var id:String?,
    val name:String?,
    val description:String?,
    val ingredients:List<String>?,
    val paragraphs:List<String>?,
    val images:String?,
    val author:String?
)
