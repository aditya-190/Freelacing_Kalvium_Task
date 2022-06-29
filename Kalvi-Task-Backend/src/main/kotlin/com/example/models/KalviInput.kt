package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class KalviInput(
    val code: String,
    val language: String
)
