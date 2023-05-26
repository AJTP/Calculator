package com.example.calculator.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

private const val TEXT_SCALE_REDUCTION = 0.9f
@Composable
fun ResponsiveText(
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    text: String,
    initialTextSize: TextUnit,
    textAlign: TextAlign,
    fontWeight: FontWeight,
    color: Color
){
    var textSize by remember {
        mutableStateOf(initialTextSize)
    }
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        onTextLayout = { textLayoutResult:TextLayoutResult ->
            if(text.isEmpty()){
                textSize = initialTextSize
            }
            if(textLayoutResult.hasVisualOverflow){
                textSize *= TEXT_SCALE_REDUCTION
            }
        },
        textAlign = textAlign,
        fontWeight = fontWeight,
        color = color,
        fontSize = textSize
    )
}