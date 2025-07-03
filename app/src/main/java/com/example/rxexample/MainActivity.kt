package com.example.rxexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rxexample.data.ProductService
import com.example.rxexample.data.data_source.MocSource
import com.example.rxexample.domain.models.Product
import com.example.rxexample.ui.theme.RxExampleTheme
import io.reactivex.schedulers.Schedulers

class MainActivity : ComponentActivity() {
    private val service by lazy { ProductService() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RxExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductScreen(
                        modifier = Modifier.padding(innerPadding),
                        onClickButton = { id, onSuccess, onError ->
                            service.getProductsByCategory(id)
                                .observeOn(Schedulers.computation())
                                .subscribe(
                                    { onSuccess(it) },
                                    { error -> onError(error.message) }
                                )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    onClickButton: (categoryId: Long, onSuccess: (products: List<Product>) -> Unit, onError: (errorMessage: String?) -> Unit) -> Unit
) {
    val text = remember { mutableStateOf("Выберите категорию") }

    Column(modifier = modifier) {
        Text(text = text.value)
        Row {
            MocSource.categories.forEach { category ->
                TextButton(
                    onClick = {
                        onClickButton(
                            category.id,
                            { products -> text.value = products.map { it.name }.toString() },
                            { errorMessage -> text.value = "Ошибка: $errorMessage" }
                        )
                    }
                ) { Text(text = category.name) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    RxExampleTheme {
        ProductScreen { _, _, _ -> }
    }
}