package com.example.rxexample.data.repositories

import com.example.rxexample.data.data_source.MocSource
import com.example.rxexample.domain.models.Subcategory
import com.example.rxexample.domain.repositories.CategoryRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CategoryRepositoryImpl : CategoryRepository {
    override fun getSubcategories(categoryId: Long): Single<List<Subcategory>> {
        val subcategories = MocSource.subcategories.filter { it.categoryId == categoryId }

        return Single.just(subcategories).subscribeOn(Schedulers.io())
    }
}