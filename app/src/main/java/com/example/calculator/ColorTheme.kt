package com.example.calculator

import androidx.compose.ui.graphics.Color
import com.example.calculator.ui.theme.*

enum class ColorTheme(val colors:List<Color>) {
    PURPLES(listOf(Purple80, PurpleGrey80,Pink80)),
    ORANGES(listOf(Orange80, Skin80,Yellow80)),
    GREENS(listOf(Lime80,Pistaccio80,Turquoise80)),
    IPHONE(listOf(HighOrange80,DarkGray80,Gray80 ))
}