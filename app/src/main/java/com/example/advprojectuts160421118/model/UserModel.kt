package com.example.advprojectuts160421118.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("iduser")
    val id: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nama_depan")
    val namaDepan: String,
    @SerializedName("nama_belakang")
    val namaBelakang: String,
    @SerializedName("email")
    val email: String,

)