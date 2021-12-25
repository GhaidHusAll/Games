package com.example.thirdparty_ghh.Model

data class Data(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>,
    val user_platforms: Boolean
)