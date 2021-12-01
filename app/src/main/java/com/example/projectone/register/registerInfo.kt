package com.example.projectone.register

data class i(
    val `data`: Data,
    val error: Boolean,
    val message: String
)

data class Data(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val firstName: String,
    val mobile: String,
    val password: String
)