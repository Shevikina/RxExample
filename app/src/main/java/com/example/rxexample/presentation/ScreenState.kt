package com.example.rxexample.presentation

import com.example.rxexample.domain.models.Product

data class ScreenState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val errorMessage: String? = null
)
