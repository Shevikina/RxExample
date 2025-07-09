package com.example.rxexample.data.usecases

import com.example.rxexample.data.repositories.CategoryRepositoryImpl
import com.example.rxexample.data.repositories.ProductRepositoryImpl
import com.example.rxexample.domain.models.Product
import com.example.rxexample.domain.repositories.CategoryRepository
import com.example.rxexample.domain.repositories.ProductRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ProductUseCase(
    private val categoryRepo: CategoryRepository = CategoryRepositoryImpl(),
    private val productRepo: ProductRepository = ProductRepositoryImpl()
) {
    fun getProductsByCategory(categoryId: Long): Single<List<Product>> {
        return categoryRepo.getSubcategories(categoryId)
            .flatMap { subcategories ->
                val subcategoryIds = subcategories.map { it.id }
                productRepo.getProductsBySubcategoryIds(subcategoryIds)
            }.subscribeOn(Schedulers.io())
    }
}