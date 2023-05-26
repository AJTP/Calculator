package com.example.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.calculator.Button
import com.example.calculator.CalculatorAction
import com.example.calculator.CalculatorOperation
import com.example.calculator.CalculatorViewModel
import kotlin.reflect.KFunction0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit,
    onMoreVertClick: KFunction0<Unit>
) {
    val firstColor = viewModel.state.theme.colors[0]
    val secondColor = viewModel.state.theme.colors[1]
    val thirdColor  = viewModel.state.theme.colors[2]

    val buttons = listOf(
        listOf(
            Button("AC", thirdColor, 2f, CalculatorAction.Clear),
            Button("Del", thirdColor, 1f, CalculatorAction.Delete),
            Button("/", firstColor, 1f, CalculatorAction.Operation(CalculatorOperation.Divide)),
        ),
        listOf(
            Button("7", secondColor, 1f, CalculatorAction.Number(7)),
            Button("8", secondColor, 1f, CalculatorAction.Number(8)),
            Button("9", secondColor, 1f, CalculatorAction.Number(9)),
            Button("x", firstColor, 1f, CalculatorAction.Operation(CalculatorOperation.Multiply)),
        ),
        listOf(
            Button("4", secondColor, 1f, CalculatorAction.Number(4)),
            Button("5", secondColor, 1f, CalculatorAction.Number(5)),
            Button("6", secondColor, 1f, CalculatorAction.Number(6)),
            Button("-", firstColor, 1f, CalculatorAction.Operation(CalculatorOperation.Substract)),
        ),
        listOf(
            Button("1", secondColor, 1f, CalculatorAction.Number(1)),
            Button("2", secondColor, 1f, CalculatorAction.Number(2)),
            Button("3", secondColor, 1f, CalculatorAction.Number(3)),
            Button("+", firstColor, 1f, CalculatorAction.Operation(CalculatorOperation.Add)),
        ),
        listOf(
            Button(".", thirdColor, 1f, CalculatorAction.Decimal),
            Button("0", secondColor, 1f, CalculatorAction.Number(0)),
            Button("=", thirdColor, 2f, CalculatorAction.Calculate),
        ),
    )
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopBar(onMoreVertClick)
        }
    ) {
        ButtonPad(viewModel.state, buttonSpacing, modifier.padding(it), buttons, onAction)
    }
    ColorThemeDialog(viewModel.state.showDialog,
        onDismiss = { viewModel.hideDialog() },
        onThemeSelected = { viewModel.onThemeSelected(it) }
    )

}