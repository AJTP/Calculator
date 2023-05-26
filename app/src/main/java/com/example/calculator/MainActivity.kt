package com.example.calculator

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.components.Calculator
import com.example.calculator.ui.theme.CalculatorTheme

private const val USER_PREFERENCES_NAME = "user_preferences"

class MainActivity : ComponentActivity() {


    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val buttonSpacing = 8.dp
                Calculator(
                    viewModel = viewModel,
                    onMoreVertClick = viewModel::alternateShow,
                    onAction = viewModel::onAction,
                    buttonSpacing = buttonSpacing,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .padding(16.dp)
                )
            }
        }
    }
}