package com.example.calculator


data class CalculatorState(
    var showDialog: Boolean = false,
    val number1: String = "",
    val number2: String = "",
    val operation: CalculatorOperation? = null,
    var theme: ColorTheme = ColorTheme.IPHONE
)
