package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class KalviOutput(
    val success: Boolean,
    val output: String,
    val language: String,
    val timestamp: Long
)
