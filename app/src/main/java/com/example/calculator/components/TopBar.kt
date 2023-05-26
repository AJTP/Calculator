package com.example.calculator.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlin.reflect.KFunction0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(alternateShow: KFunction0<Unit>) {
    TopAppBar(
        title = { Text(text = "") },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent) ,
        navigationIcon = {
            IconButton(onClick = { alternateShow() }, colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)) {
                Icon(Icons.Default.MoreVert, contentDescription = "More Options")
            }
        },

        actions = {}
    )
}