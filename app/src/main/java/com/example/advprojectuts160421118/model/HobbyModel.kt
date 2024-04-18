package com.example.advprojectuts160421118.model

import com.google.gson.annotations.SerializedName

data class Hobby(
    @SerializedName("id")
    val id:Int?,
    @SerializedName("judul")
    val judul:String?,
    @SerializedName("nama")
    val nama:String?,
    @SerializedName("deskripsi")
    val desc:String?,
    @SerializedName("isi")
    val isi:String?,
    @SerializedName("photo_url")
    val photoUrl:String?
)