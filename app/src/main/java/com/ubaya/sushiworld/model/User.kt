package com.ubaya.sushiworld.model

data class User(
    var id:String?,
    val username:String?,
    val nama_depan:String?,
    val nama_belakang:String?,
    val email:String?,
    val password:String?,
    val photo_url:String?
)
