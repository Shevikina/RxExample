package com.example.rxexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.rxexample.data.data_source.MocSource
import com.example.rxexample.presentation.MainViewModel
import com.example.rxexample.presentation.ScreenEvent
import com.example.rxexample.presentation.ScreenState
import com.example.rxexample.ui.theme.RxExampleTheme

class MainActivity : ComponentActivity() {
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState = model.uiState.observeAsState(ScreenState())

            RxExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = uiState,
                        onClickButton = { id ->
                            model.handleEvent(ScreenEvent.ProductByCategoryEvent(id))
                        })
                }
            }
        }
    }

    override fun onDestroy() {
        model.handleEvent(ScreenEvent.CleanDisposableEvent)
        super.onDestroy()
    }
}

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    uiState: State<ScreenState>,
    onClickButton: (categoryId: Long) -> Unit
) {
    var text by remember { mutableStateOf("") }
    uiState.value.apply {
        if (!isLoading) {
            text = when {
                errorMessage != null -> errorMessage
                products.isEmpty() -> stringResource(R.string.select_category)
                else -> products.map { it.name }.toString()
            }
        }
    }

    Column(modifier = modifier) {
        Text(text = text)
        Row {
            MocSource.categories.forEach { category ->
                TextButton(
                    onClick = { onClickButton(category.id) }
                ) { Text(text = category.name) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    RxExampleTheme {
        ProductScreen(
            modifier = Modifier,
            uiState = remember { mutableStateOf(ScreenState()) },
            onClickButton = {})
    }
}