package com.example.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.calculator.Button
import com.example.calculator.CalculatorAction
import com.example.calculator.CalculatorState

@Composable
fun ButtonPad(
    state: CalculatorState,
    buttonSpacing: Dp,
    modifier: Modifier,
    buttons: List<List<Button>>,
    onAction: (CalculatorAction) -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            ResponsiveText(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                initialTextSize = 60.sp,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Light,
                color = Color.White,
            )

            buttons.forEach { row ->
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    row.forEach { button ->
                        CalculatorButton(
                            symbol = button.symbol,
                            modifier = Modifier
                                .background(button.color)
                                .aspectRatio(button.aspectRatio)
                                .weight(button.aspectRatio),
                            onClick = {
                                onAction(button.onClick)
                            }
                        )
                    }
                }
            }
        }
    }
}