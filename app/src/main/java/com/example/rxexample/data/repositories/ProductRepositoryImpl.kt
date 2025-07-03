package com.example.rxexample.data.repositories

import com.example.rxexample.data.data_source.MocSource
import com.example.rxexample.domain.models.Product
import com.example.rxexample.domain.repositories.ProductRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ProductRepositoryImpl : ProductRepository {
    override fun getProductsBySubcategoryIds(subcategoryIds: List<Long>): Single<List<Product>> {
        val products = subcategoryIds.flatMap { id ->
            MocSource.products.filter { it.subcategoryId == id }
        }

        return Single.just(products).subscribeOn(Schedulers.io())
    }
}