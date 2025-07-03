package com.example.rxexample.domain.repositories

import com.example.rxexample.domain.models.Subcategory
import io.reactivex.Single

interface CategoryRepository {
    fun getSubcategories(categoryId: Long): Single<List<Subcategory>>
}