package com.example.calculator

import androidx.compose.ui.graphics.Color

class Button(
    val symbol: String,
    val color: Color,
    val aspectRatio: Float,
    val onClick: CalculatorAction
)