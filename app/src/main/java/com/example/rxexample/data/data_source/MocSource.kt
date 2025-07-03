package com.example.rxexample.data.data_source

import com.example.rxexample.domain.models.Category
import com.example.rxexample.domain.models.Product
import com.example.rxexample.domain.models.Subcategory

object MocSource {
    val categories = listOf(
        Category(id = 1, name = "Еда"),
        Category(id = 2, name = "Хозтовары")
    )

    val subcategories = listOf(
        Subcategory(id = 1, categoryId = 1, name = "Фрукты"),
        Subcategory(id = 2, categoryId = 1, name = "Мясные изделия"),
        Subcategory(id = 3, categoryId = 2, name = "Уборка"),
        Subcategory(id = 4, categoryId = 2, name = "Гигиена")
    )

    val products = listOf(
        Product(id = 11, subcategoryId = 1, name = "Яблоко"),
        Product(id = 22, subcategoryId = 1, name = "Груша"),
        Product(id = 33, subcategoryId = 2, name = "Колбаса"),
        Product(id = 44, subcategoryId = 2, name = "Рулька"),
        Product(id = 55, subcategoryId = 3, name = "Бумажные полотенца"),
        Product(id = 66, subcategoryId = 4, name = "Мыло")
    )
}