package com.example.rxexample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxexample.data.usecases.ProductUseCase
import com.example.rxexample.domain.models.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val disposable = CompositeDisposable()

    private val productUseCase by lazy { ProductUseCase() }

    private val _uiState = MutableLiveData(ScreenState())
    val uiState: LiveData<ScreenState> = _uiState

    fun handleEvent(event: ScreenEvent) {
        when (event) {
            is ScreenEvent.ProductByCategoryEvent -> getProductsByCategory(event.categoryId)
            is ScreenEvent.CleanDisposableEvent -> cleanDisposable()
        }
    }

    private fun getProductsByCategory(id: Long) {
        startLoad()

        disposable.add(
            productUseCase.getProductsByCategory(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccess(it) }, { error -> onError(error.message) })
        )
    }

    private fun startLoad() {
        _uiState.postValue(
            _uiState.value?.copy(
                isLoading = true
            )
        )
    }

    private fun onSuccess(product: List<Product>) {
        _uiState.postValue(
            _uiState.value?.copy(
                isLoading = false,
                products = product,
                errorMessage = null
            )
        )
    }

    private fun onError(message: String?) {
        _uiState.postValue(
            _uiState.value?.copy(
                isLoading = false,
                errorMessage = message
            )
        )
    }

    private fun cleanDisposable() {
        disposable.clear()
    }
}