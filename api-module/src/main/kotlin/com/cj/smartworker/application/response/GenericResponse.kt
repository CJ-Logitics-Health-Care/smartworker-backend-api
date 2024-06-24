package com.cj.smartworker.application.response

data class GenericResponse<T>(
    val data: T,
    val statusCode: Int = 200,
    val messages: List<String?> = mutableListOf(),
    val success: Boolean = true,
)
