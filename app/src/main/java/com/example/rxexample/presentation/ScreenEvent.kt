package com.example.rxexample.presentation

sealed class ScreenEvent {
    data class ProductByCategoryEvent(val categoryId: Long) : ScreenEvent()
    data object CleanDisposableEvent : ScreenEvent()
}
