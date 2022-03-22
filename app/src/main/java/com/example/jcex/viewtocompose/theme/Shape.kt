package com.example.viewtocomposetheming.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Theme2Shapes = Shapes(
    small = CutCornerShape(topStart = 16.dp),
    medium = CutCornerShape(topStart = 24.dp),
    large = RoundedCornerShape(32.dp)
)