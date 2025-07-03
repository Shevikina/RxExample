package com.example.rxexample.domain.repositories

import com.example.rxexample.domain.models.Product
import io.reactivex.Single

interface ProductRepository {
    fun getProductsBySubcategoryIds(subcategoryIds: List<Long>): Single<List<Product>>
}