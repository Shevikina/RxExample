package com.example.rxexample.data.repositories

import com.example.rxexample.data.data_source.MocSource
import com.example.rxexample.domain.models.Subcategory
import com.example.rxexample.domain.repositories.CategoryRepository
import io.reactivex.Single

class CategoryRepositoryImpl : CategoryRepository {
    override fun getSubcategories(categoryId: Long): Single<List<Subcategory>> {
        return Single.fromCallable {
            MocSource.subcategories.filter { it.categoryId == categoryId }
        }
    }
}