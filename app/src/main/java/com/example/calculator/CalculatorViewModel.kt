package com.example.calculator

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import java.text.DecimalFormat
private const val USER_PREFERENCES_NAME = "user_preferences"

class CalculatorViewModel : ViewModel() {
    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )
    var state by mutableStateOf(CalculatorState())
        private set

    fun onThemeSelected(theme: ColorTheme) {
        state.theme = theme
        //GUARDAR EN DATA STORE

        hideDialog()
    }

    fun hideDialog() {
        state = state.copy(showDialog = false)
    }

    fun alternateShow() {
        state = state.copy(showDialog = !state.showDialog)
    }

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Clear -> clearState()
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Operation -> enterOperation(action.operation)
        }
    }

    private fun clearState() {
        val theme = state.theme
        state = CalculatorState()
        state.theme = theme
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun performDeletion() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if (state.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }

        if (state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }
        if (!state.number2.contains(".") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = when (state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Substract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                null -> return
            }
            state = state.copy(
                number1 = reduceDecimals(result, 8).toString(),
                number2 = "",
                operation = null
            )
        }
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }

    private fun reduceDecimals(value: Double, decimalPlaces: Int): Double {
        val pattern = StringBuilder("#.")
        repeat(decimalPlaces) {
            pattern.append("#")
        }
        val decimalFormat = DecimalFormat(pattern.toString())
        return decimalFormat.format(value).toDouble()
    }
}