package com.example.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.calculator.ColorTheme

@Composable
fun ColorThemeDialog(show: Boolean, onDismiss: () -> Unit, onThemeSelected: (ColorTheme) -> Unit) {
    var selectedOption by remember { mutableStateOf(ColorTheme.IPHONE) }
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Color Scheme",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(16.dp))
                RadioButtonGroup(selectedOption = selectedOption) { newColorTheme ->
                    selectedOption = newColorTheme
                }
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onThemeSelected(selectedOption)
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@Composable
fun RadioButtonGroup(selectedOption: ColorTheme, onOptionSelected: (ColorTheme) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        Text("Choose an option:")
        Spacer(modifier = Modifier.height(8.dp))
        RadioGroup(selectedOption) { newOption ->
            onOptionSelected(newOption)
        }
    }
}

@Composable
fun RadioGroup(selectedOption: ColorTheme, onOptionSelected: (ColorTheme) -> Unit) {
    val options = ColorTheme.values()

    options.forEach { option ->
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = option == selectedOption,
                onClick = { onOptionSelected(option) }
            )
            option.colors.forEach {
                Box(
                    modifier = Modifier.width(50.dp).height(25.dp)
                        .background(
                            it,
                            RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(text = "")
                }
            }
        }
    }
}







