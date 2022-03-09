package com.example.jcex.theming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class ThemingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home()
        }
    }
}